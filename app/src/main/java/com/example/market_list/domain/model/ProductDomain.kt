package com.example.market_list.domain.model

import kotlin.time.times

data class ProductDomain(
    val id: Int = 0,
    val name: String,
    val unitPrice: Double,
    val amount: Double,
    var totalPrice: Double = unitPrice * amount,
    val marketListId: Int
)
