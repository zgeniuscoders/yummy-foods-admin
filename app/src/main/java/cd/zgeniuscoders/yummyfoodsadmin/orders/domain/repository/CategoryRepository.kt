package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository

import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.CategoriesDto
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<Resource<CategoriesDto>>

}