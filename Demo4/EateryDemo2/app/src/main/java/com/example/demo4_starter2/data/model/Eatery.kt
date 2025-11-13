package com.example.demo4_starter2.data.model

data class Eatery(
    val eateryName: String,
    val location: Location,
    val paymentOptions: List<PaymentType>,
    val isOpen: Boolean
)

enum class PaymentType {
    BRB, SWIPES, CASH
}

enum class Location {
    NORTH, CENTRAL, WEST
}