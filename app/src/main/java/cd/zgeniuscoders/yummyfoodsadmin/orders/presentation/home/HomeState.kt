package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home

import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Order

data class HomeState(
    val flashMessage: String = "",
    val orders: List<Order> = emptyList()
)
