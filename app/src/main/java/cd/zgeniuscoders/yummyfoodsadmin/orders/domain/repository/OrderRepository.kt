package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository

import cd.zgeniuscoders.yummyfoods.food.data.dto.OrdersDto
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun cancelOrder(orderId: String, userId: String): Flow<Resource<Boolean>>

    suspend fun markAsDelivered(orderId: String, userId: String): Flow<Resource<Boolean>>

    suspend fun getOrders(): Flow<Resource<OrdersDto>>
}