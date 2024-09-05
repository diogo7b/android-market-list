package com.example.market_list.domain.model

data class FullListDomain(
    val marketList: MarketListDomain,
    val products: List<ProductDomain>
)
