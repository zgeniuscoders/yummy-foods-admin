package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository

import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomerCountDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomerDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CustomersDto
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    suspend fun getCustomers(): Flow<Resource<CustomersDto>>

    suspend fun getTotalCustomersCount(): Flow<Resource<CustomerCountDto>>

    suspend fun getCustomer(customerId: String): Flow<Resource<CustomerDto>>

}