package com.example.market_list.domain.model

data class ItemListDomain(
    val id: Int,
    val name: String,
    val unitPrice: String,
    val amount: Int,
    val totalPrice: String
)
