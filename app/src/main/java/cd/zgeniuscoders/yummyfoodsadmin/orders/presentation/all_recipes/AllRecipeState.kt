package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes

import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Recipe

data class AllRecipeState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val flashMessage: String = ""
)