package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cd.zgeniuscoders.yummyfoodsadmin.MenuItem

@Composable
fun BottomPageComponent(
    menuItems: List<MenuItem>,
    selectedItem: Int,
    onSelectedItem: (index: Int) -> Unit
) {
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