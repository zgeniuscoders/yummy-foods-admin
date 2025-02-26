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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes.AllRecipePage
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components.BottomPageComponent
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home.HomePage
import cd.zgeniuscoders.yummyfoodsadmin.ui.theme.YummyFoodsAdminTheme
import cd.zgeniuscoders.yummyfoodsadmin.util.Routes

data class MenuItem(
    val icon: ImageVector,
    val title: String,
    val unSelectedIcon: ImageVector,
    val onClick: () -> Unit
)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YummyFoodsAdminTheme {

                val navController = rememberNavController()
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
                        TopAppBar(title = {
                            Text("Yummy Food")
                        })
                    },
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    },
                    bottomBar = {
                        BottomPageComponent(menuItems, selectedItem) {
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

                        }
                    }

                }

            }
        }
    }
}
