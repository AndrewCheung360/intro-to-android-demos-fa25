package com.example.demo4_starter2.data.repository

import com.example.demo4_starter2.data.model.Eatery
import com.example.demo4_starter2.data.model.Location
import com.example.demo4_starter2.data.model.PaymentType
import javax.inject.Inject

class EateryRepository @Inject constructor() {
    fun getAllEateries(): List<Eatery> = listOf(
        Eatery("Morrison", Location.NORTH, listOf(PaymentType.SWIPES, PaymentType.BRB), true),
        Eatery("Appel", Location.NORTH, listOf(PaymentType.SWIPES, PaymentType.BRB), false),
        Eatery("Louie's", Location.NORTH, listOf(PaymentType.CASH), true),
        Eatery("Risley", Location.NORTH, listOf(PaymentType.SWIPES), false),
        Eatery("Okenshields", Location.CENTRAL, listOf(PaymentType.SWIPES), true),
        Eatery("Libe Cafe", Location.CENTRAL, listOf(PaymentType.CASH, PaymentType.BRB), true),
        Eatery("Trillium", Location.CENTRAL, listOf(PaymentType.CASH, PaymentType.BRB), false),
        Eatery("Cook", Location.WEST, listOf(PaymentType.SWIPES), true),
        Eatery("Rose", Location.WEST, listOf(PaymentType.SWIPES), false),
        Eatery("Keeton", Location.WEST, listOf(PaymentType.SWIPES), false)
    )
}