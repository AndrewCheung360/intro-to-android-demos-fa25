package com.example.demo4_starter2.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo4_starter2.data.model.Location
import com.example.demo4_starter2.data.model.PaymentType
import kotlin.collections.joinToString

@Composable
fun EateryCard(
    eateryName: String,
    location: Location,
    paymentTypes: List<PaymentType>,
    isOpen: Boolean
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(color = Color(0xff5ecfdb))
            )
            Box(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = eateryName,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = location.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Accepts ${paymentTypes.joinToString(separator = ", ")}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Text(
                        text = if (isOpen) "Open" else "Closed",
                        fontSize = 24.sp,
                        color = if (isOpen) Color.Green else Color.Red,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }
    }
}

@Composable
@Preview
private fun EateryCardPreview() {
    EateryCard("Morrison", Location.NORTH, listOf(PaymentType.BRB, PaymentType.SWIPES), true)
}