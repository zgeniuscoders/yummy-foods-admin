package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cd.zgeniuscoders.yummyfoodsadmin.MenuItem
import cd.zgeniuscoders.yummyfoodsadmin.util.Routes

@Composable
fun BottomPageComponent(
    currentRoute: Routes?,
    menuItems: List<MenuItem>,
    selectedItem: Int,
    onSelectedItem: (index: Int) -> Unit
) {
    val mainPages = listOf(
        Routes.HomePage,
        Routes.RecipePage
    )

    if(currentRoute in mainPages){
        NavigationBar {
            menuItems.forEachIndexed { index, menuItem ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = {
                        onSelectedItem(index)
                        menuItem.onClick()
                    },
                    label = {
                        Text(menuItem.title)
                    },
                    icon = {
                        if (selectedItem == index) {
                            Icon(menuItem.icon, contentDescription = menuItem.title)
                        } else {
                            Icon(
                                menuItem.unSelectedIcon,
                                contentDescription = menuItem.title
                            )
                        }
                    }
                )
            }
        }
    }
}