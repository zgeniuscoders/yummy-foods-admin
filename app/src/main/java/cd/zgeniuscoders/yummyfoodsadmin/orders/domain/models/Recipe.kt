package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val price: Double,
    val recipePhoto: String,
)
