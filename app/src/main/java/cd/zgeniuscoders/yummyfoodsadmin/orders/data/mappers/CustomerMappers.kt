package cd.zgeniuscoders.yummyfoodsadmin.orders.data.mappers

import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomerDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomersDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Customer

fun CustomersDto.toCustomerListModel(): List<Customer> {
    return data.map {
        Customer(
            userId = it.userId,
            username = it.username,
            email = it.email,
            profilePictureUrl = it.profilePictureUrl,
            orders = emptyList()
        )
    }
}

fun CustomerDto.toCustomerModel(): Customer {
    return Customer(
        userId = data.userId,
        username = data.username,
        email = data.email,
        profilePictureUrl = data.profilePictureUrl,
        orders = emptyList()
    )
}