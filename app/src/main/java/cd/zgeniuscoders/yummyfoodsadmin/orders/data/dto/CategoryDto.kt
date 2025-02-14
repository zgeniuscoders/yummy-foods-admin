package cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto

import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Category

data class CategoryDto(
    val data: Category,
    val error: String? = null
)

data class CategoriesDto(
    val data: List<Category>,
    val error: String? = null
)