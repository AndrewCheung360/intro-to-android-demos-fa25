package com.example.demo4_starter2.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo4_starter2.data.model.Location

@Composable
fun FilterRow(
    northSelected: Boolean,
    centralSelected: Boolean,
    westSelected: Boolean,
    isOpenSelected: Boolean,
    onLocationSelected: (Location) -> Unit,
    onIsOpenFilterToggled: () -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        item{
            Spacer(modifier = Modifier.padding(start = 8.dp))
        }
        item {
            FilterButton(
                label = "North",
                selected = northSelected,
                onSelect = { onLocationSelected(Location.NORTH) }
            )
        }
        item {
            FilterButton(
                label = "Central",
                selected = centralSelected,
                onSelect = { onLocationSelected(Location.CENTRAL) }
            )
        }
        item {
            FilterButton(
                label = "West",
                selected = westSelected,
                onSelect = { onLocationSelected(Location.WEST) }
            )
        }
        item {
            FilterButton(
                label = "Now Open",
                selected = isOpenSelected,
                onSelect = onIsOpenFilterToggled
            )
        }
        item{
            Spacer(modifier = Modifier.padding(end = 8.dp))
        }
    }

}

@Composable
private fun FilterButton(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Button(
        onClick = onSelect,
        colors = if (selected) ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ) else ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterBarPreview() {
    FilterRow(
        northSelected = true,
        centralSelected = false,
        westSelected = false,
        isOpenSelected = true,
        onLocationSelected = {},
        onIsOpenFilterToggled = {}
    )
}