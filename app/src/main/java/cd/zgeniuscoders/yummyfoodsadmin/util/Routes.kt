package cd.zgeniuscoders.yummyfoodsadmin.util

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {

    @Serializable
    data object MainPagesNavGraph: Routes

    @Serializable
    data object ProfileNavGraph : Routes

    @Serializable
    data object Profile : Routes

    @Serializable
    data object FavoriteFood : Routes

    @Serializable
    data object AllOrder : Routes

    @Serializable
    data object Login : Routes

    @Serializable
    data object Logout : Routes

    @Serializable
    data object Home : Routes

    @Serializable
    data object OrderPage : Routes

    @Serializable
    data object Settings : Routes

    @Serializable
    data object Search : Routes

    @Serializable
    data class Detail(
        val id: String
    ) : Routes


}