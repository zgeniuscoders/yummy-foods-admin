package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import cd.zgeniuscoders.yummyfoodsadmin.R
import cd.zgeniuscoders.yummyfoodsadmin.util.Routes


@Composable
fun AddRecipeFloatingActionButtonComponent(currentRoute: Routes?, navController: NavHostController) {
    if(currentRoute == Routes.RecipePage){
        FloatingActionButton(
            onClick = {
                navController.navigate(Routes.AddRecipePage)
            }
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = stringResource(R.string.add_recipes_content_description)
            )
        }
    }
}