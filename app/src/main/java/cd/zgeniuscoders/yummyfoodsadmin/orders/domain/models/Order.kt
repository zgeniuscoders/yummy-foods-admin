package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: String = "",
    val recipeId: String,
    val recipeImage: String,
    val userId: String,
    val recipeName: String,
    val recipePrice: Double,
    val qty: Int,
    val university: String,
    val phoneNumber: String,
    val orderType: Boolean,
    val orderStatus: String,
    var revealOrder: Boolean = false,
    val deliveredAt: String? = null,
    val deliveredTime: String? = null
)
