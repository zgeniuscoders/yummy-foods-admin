package cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto

data class RecipeDto(
    val data: RecipesDtoData,
    val error: String? = null
)

data class RecipesDto(
    val data: List<RecipesDtoData>,
    val error: String? = null
)

data class RecipesDtoData(
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val recipePhoto: String = "",
)