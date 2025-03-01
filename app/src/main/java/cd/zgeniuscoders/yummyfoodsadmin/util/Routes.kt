package cd.zgeniuscoders.yummyfoodsadmin.util

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object MainPagesNavGraph: Routes

    @Serializable
    data object HomePage:Routes

    @Serializable
    data object OrderPage: Routes

    @Serializable
    data object RecipePage: Routes

    @Serializable
    data object AddRecipePage: Routes

    @Serializable
    data class EditRecipePage(
        val recipeId: String
    ): Routes
}