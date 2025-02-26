package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cd.zgeniuscoders.yummyfoodsadmin.util.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBarComponent(currentRoute: Routes?) {
    val title = when (currentRoute) {
        Routes.AddRecipePage -> "Ajout d'un repas"
        else -> "Yummy Food"
    }

    TopAppBar(title = {
        Text(title)
    })
}