package com.example.market_list.domain.model

data class ProductDomain(
    val id: Int = 0,
    val name: String,
    val price: Double = 0.0,
    val amount: Double = 0.0,
    val marketListId: Int
)
