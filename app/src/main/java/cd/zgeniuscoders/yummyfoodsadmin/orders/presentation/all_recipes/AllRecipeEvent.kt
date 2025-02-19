package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes

sealed interface AllRecipeEvent {

    data class OnDeleteRecipe(val recipeId: String): AllRecipeEvent

}