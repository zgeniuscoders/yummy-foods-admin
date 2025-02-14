package cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CustomerDto(
    val data: CustomerDtoData
)

data class CustomersDto(
    val data: List<CustomerDtoData>
)

data class CustomerCountDto(
    val data: Int
)

@Serializable
data class CustomerDtoData(
    @SerialName("user_id")
    val userId: String,
    @SerialName("username")
    val username: String,
    @SerialName("profile_picture_url")
    val profilePictureUrl: String?,
    @SerialName("email")
    val email: String,
)