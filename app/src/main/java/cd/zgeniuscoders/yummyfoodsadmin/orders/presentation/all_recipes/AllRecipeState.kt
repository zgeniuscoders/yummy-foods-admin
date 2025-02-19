package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes

data class AllRecipeState(
    val recipes: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val flashMessage: String = ""
)