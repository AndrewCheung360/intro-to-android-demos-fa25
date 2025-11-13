package com.example.demo4_starter2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.demo4_starter2.data.model.Eatery
import com.example.demo4_starter2.data.model.Location
import com.example.demo4_starter2.data.model.PaymentType
import com.example.demo4_starter2.eatery.EateryViewModel
import com.example.demo4_starter2.ui.components.EateryCard
import com.example.demo4_starter2.ui.components.FilterRow


@Composable
fun EateryScreen(
    //TODO: Add a viewModel parameter with a default hiltViewModel() value
    viewModel: EateryViewModel = hiltViewModel()
) {
    //TODO: Define a state and collect the uiState flow from the viewModel as a State
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    //TODO: Change this composable call to use the uiState you defined and functions from the viewModel.
    EateryScreenContent(
        uiState,
        viewModel::onLocationSelected,
        viewModel::onNowOpenSelected
    )
}

@Composable
private fun EateryScreenContent(
    uiState: EateryViewModel.EateryUIState,
    onLocationSelected: (Location) -> Unit,
    onNowOpenSelected: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Eatery",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        FilterRow(
            northSelected = uiState.northSelected,
            centralSelected = uiState.centralSelected,
            westSelected = uiState.westSelected,
            isOpenSelected = uiState.nowOpen,
            onLocationSelected = onLocationSelected,
            onIsOpenFilterToggled = onNowOpenSelected
        )
        //TODO: Make a LazyColumn to display all the filteredEateries from the ViewModel
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 10.dp)) {
            items(uiState.filteredEateries) {eatery ->
                EateryCard(
                    eateryName = eatery.eateryName,
                    location = eatery.location,
                    paymentTypes = eatery.paymentOptions,
                    isOpen = eatery.isOpen
                )
                
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EateryScreenContentPreview() {
    EateryScreenContent(
        EateryViewModel.EateryUIState(
            northSelected = true,
            centralSelected = false,
            westSelected = false,
            nowOpen = false,
            allEateries = listOf(
                Eatery(
                    "Morrison",
                    Location.NORTH,
                    listOf(PaymentType.SWIPES, PaymentType.BRB),
                    true
                ),
                Eatery("Appel", Location.NORTH, listOf(PaymentType.SWIPES, PaymentType.BRB), false),
                Eatery("Louie's", Location.NORTH, listOf(PaymentType.CASH), true),
                Eatery("Risley", Location.NORTH, listOf(PaymentType.SWIPES), false),
                Eatery("Okenshields", Location.CENTRAL, listOf(PaymentType.SWIPES), true),
                Eatery(
                    "Libe Cafe",
                    Location.CENTRAL,
                    listOf(PaymentType.CASH, PaymentType.BRB),
                    true
                ),
                Eatery(
                    "Trillium",
                    Location.CENTRAL,
                    listOf(PaymentType.CASH, PaymentType.BRB),
                    false
                ),
                Eatery("Cook", Location.WEST, listOf(PaymentType.SWIPES), true),
                Eatery("Rose", Location.WEST, listOf(PaymentType.SWIPES), false),
                Eatery("Keeton", Location.WEST, listOf(PaymentType.SWIPES), false)
            )
        ), {}, {})
}