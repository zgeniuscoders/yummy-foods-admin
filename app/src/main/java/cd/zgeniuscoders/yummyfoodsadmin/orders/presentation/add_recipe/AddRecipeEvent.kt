package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.add_recipe

sealed interface AddRecipeEvent {

    data class OnNameChange(val name: String) : AddRecipeEvent
    data class OnDescriptionChange(val description: String) : AddRecipeEvent
    data class OnCategoryChange(val category: String) : AddRecipeEvent
    data class OnPriceChange(val price: String) : AddRecipeEvent
    data class OnPhotoUrlChange(val url: String) : AddRecipeEvent

    data object OnAddRecipe : AddRecipeEvent

}