package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.add_recipe

data class AddRecipeState(
    val name: String = "",
    val nameError:String? = null,
    val category: String = "",
    val categoryError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val price: String = "",
    val priceError: String? = null,
    val recipePhoto: String = "",
    val recipePhotoError: String? = null,
    val isLoading: Boolean = false,
    val flashMessage: String = ""
)
