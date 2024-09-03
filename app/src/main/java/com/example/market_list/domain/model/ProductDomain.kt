package com.example.market_list.domain.model

data class ProductDomain(
    val id: Int = 0,
    val name: String,
    val unitPrice: Double,
    val amount: Int,
    val totalPrice: Double,
    val marketListId: Int
)
