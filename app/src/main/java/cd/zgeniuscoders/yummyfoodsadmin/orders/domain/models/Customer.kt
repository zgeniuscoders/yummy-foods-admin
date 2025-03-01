package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models

data class Customer(
    val userId: String,
    val username: String,
    val profilePictureUrl: String?,
    val email: String,
    val orders: List<Order>
)
