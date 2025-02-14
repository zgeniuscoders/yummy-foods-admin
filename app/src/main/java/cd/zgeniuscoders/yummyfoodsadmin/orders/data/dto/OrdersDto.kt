package cd.zgeniuscoders.yummyfoods.food.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class OrdersDto(
    val data: List<OrderDtoData>,
    val error: String? = null
)

@Serializable
data class OrderDtoData(
    @SerialName("order_id")
    var orderId: String = "",
    @SerialName("recipe_id")
    val recipeId: String = "",
    @SerialName("user_id")
    var userId: String = "",
    @SerialName("recipe_name")
    var name: String = "",
    @SerialName("recipe_price")
    var price: Double = 0.0,
    @SerialName("qty")
    val qty: String = "1",
    @SerialName("recipe_image")
    var image: String = "",
    @SerialName("university")
    val university: String = "",
    @SerialName("phone_number")
    val phoneNumber: String = "",
    @SerialName("order_type")
    val orderType: Boolean = false,
    @SerialName("order_status")
    val orderStatus: String = "en attente",
    @SerialName("delivered_at")
    val deliveredAt: String? = null,
    @SerialName("delivered_time")
    val deliveredTime: String? = null
)