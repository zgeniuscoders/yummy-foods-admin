package cd.zgeniuscoders.yummyfoodsadmin.util

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry?.fromRoute(): Routes {
    this?.destination?.route?.substringBefore("?")?.substringBefore("/")
        ?.substringAfterLast(".")?.let {
            when (it) {
                Routes.HomePage::class.simpleName -> return Routes.HomePage
                Routes.RecipePage::class.simpleName -> return Routes.RecipePage
                Routes.OrderPage::class.simpleName -> return Routes.OrderPage
                Routes.AddRecipePage::class.simpleName ->return Routes.AddRecipePage
                Routes.EditRecipePage::class.simpleName ->return Routes.EditRecipePage("")
                else -> return Routes.HomePage
            }
        }
    return Routes.HomePage
}
