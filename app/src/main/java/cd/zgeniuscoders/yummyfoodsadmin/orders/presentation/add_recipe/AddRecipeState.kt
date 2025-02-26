package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.add_recipe

data class AddRecipeState(
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: String = "",
    val recipePhoto: String = "",
    val isLoading: Boolean = false,
    val flashMessage: String = ""
)
