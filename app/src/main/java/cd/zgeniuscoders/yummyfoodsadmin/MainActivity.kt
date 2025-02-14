package cd.zgeniuscoders.yummyfoodsadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home.HomePage
import cd.zgeniuscoders.yummyfoodsadmin.ui.theme.YummyFoodsAdminTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YummyFoodsAdminTheme {

                val navController = rememberNavController()
                val snackbarHostState = SnackbarHostState()

                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text("Yummy Food")
                        })
                    },
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        HomePage(
                            navController,
                            snackbarHostState
                        )
                    }
                }

            }
        }
    }
}
