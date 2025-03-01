package cd.zgeniuscoders.yummyfoodsadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.add_recipe.AddRecipePage
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes.AllRecipePage
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components.AddRecipeFloatingActionButtonComponent
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components.BottomPageComponent
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components.DefaultTopBarComponent
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.edit_recipe.EditRecipePage
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home.HomePage
import cd.zgeniuscoders.yummyfoodsadmin.ui.theme.YummyFoodsAdminTheme
import cd.zgeniuscoders.yummyfoodsadmin.util.Routes
import cd.zgeniuscoders.yummyfoodsadmin.util.fromRoute

data class MenuItem(
    val icon: ImageVector,
    val title: String,
    val unSelectedIcon: ImageVector,
    val onClick: () -> Unit
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YummyFoodsAdminTheme {

                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.fromRoute()

                val snackbarHostState = SnackbarHostState()
                var selectedItem by remember {
                    mutableIntStateOf(0)
                }

                val menuItems = listOf(
                    MenuItem(
                        icon = Icons.Rounded.GridView,
                        title = "Commandes",
                        unSelectedIcon = Icons.Outlined.GridView,
                        onClick = {
                            navController.navigate(Routes.HomePage)
                        }
                    ),
                    MenuItem(
                        icon = Icons.Rounded.GridView,
                        title = "Recettes",
                        unSelectedIcon = Icons.Outlined.GridView,
                        onClick = {
                            navController.navigate(Routes.RecipePage)
                        }
                    ),
                )

                Scaffold(
                    topBar = {
                        DefaultTopBarComponent(currentRoute)
                    },
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    },
                    floatingActionButton = {
                        AddRecipeFloatingActionButtonComponent(currentRoute, navController)
                    },
                    bottomBar = {
                        BottomPageComponent(currentRoute,menuItems, selectedItem) {
                            selectedItem = it
                        }
                    }
                ) { innerPadding ->

                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Routes.MainPagesNavGraph
                    ) {
                        navigation<Routes.MainPagesNavGraph>(
                            startDestination = Routes.HomePage,
                        ) {

                            composable<Routes.RecipePage> {
                                AllRecipePage(
                                    navHostController = navController,
                                    snackbarHostState = snackbarHostState
                                )
                            }

                            composable<Routes.HomePage> {
                                HomePage(
                                    navHostController = navController,
                                    snackbarHostState = snackbarHostState
                                )
                            }

                            composable<Routes.AddRecipePage> {
                                AddRecipePage(
                                    navHostController = navController,
                                    snackbarHostState = snackbarHostState
                                )
                            }

                            composable<Routes.EditRecipePage> {
                                EditRecipePage(
                                    navHostController = navController,
                                    snackbarHostState = snackbarHostState
                                )
                            }

                        }
                    }

                }

            }
        }
    }
}
