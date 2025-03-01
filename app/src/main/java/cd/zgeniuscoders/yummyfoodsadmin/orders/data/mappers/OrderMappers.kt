package cd.zgeniuscoders.yummyfoodsadmin.orders.data.mappers

import cd.zgeniuscoders.yummyfoods.food.data.dto.OrdersDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Order

fun OrdersDto.toOrderList(): MutableList<Order> {
    return try {
        data.map {
            Order(
                id = it.orderId,
                recipeId = it.recipeId,
                qty = it.qty.toInt(),
                userId = it.userId,
                university = it.university,
                phoneNumber = it.phoneNumber,
                orderType = it.orderType,
                orderStatus = it.orderStatus,
                deliveredAt = it.deliveredAt,
                deliveredTime = it.deliveredTime,
                recipeName = it.name,
                recipePrice = it.price,
                recipeImage = it.image
            )
        }.toMutableList()
    }catch (e: Exception){
        e.printStackTrace()
        return data.map {
                Order(
                    id = it.orderId,
                    recipeId = it.recipeId,
                    qty = 1,
                    userId = it.userId,
                    university = it.university,
                    phoneNumber = it.phoneNumber,
                    orderType = it.orderType,
                    orderStatus = it.orderStatus,
                    deliveredAt = it.deliveredAt,
                    deliveredTime = it.deliveredTime,
                    recipeName = it.name,
                    recipePrice = it.price,
                    recipeImage = it.image
                )
            }.toMutableList()

    }
}