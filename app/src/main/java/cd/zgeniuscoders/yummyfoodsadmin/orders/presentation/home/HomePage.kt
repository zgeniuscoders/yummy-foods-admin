package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cd.zgeniuscoders.yummyfoodsadmin.R
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Order
import cd.zgeniuscoders.yummyfoodsadmin.ui.theme.YummyFoodsAdminTheme
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    val vm = koinViewModel<HomeViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()

    val onEvent = vm::onTriggerEvent

    LaunchedEffect(state.flashMessage) {
        if (state.flashMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(state.flashMessage)
        }
    }

    HomeBody(
        navHostController,
        state,
        onEvent
    )

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeBody(
    navHostController: NavHostController,
    state: HomeState,
    onEvent: (event: HomeEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tout les commandes")
                OutlinedButton(
                    onClick = {}
                ) {
                    Text("Voir tout")
                }
            }
        }
        items(state.orders) { order ->
            var isOrderDetailOpen by remember {
                mutableStateOf(false)
            }

            Card(
                onClick = {
                    isOrderDetailOpen = !isOrderDetailOpen
                }
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                    ) {
                        AsyncImage(
                            model = order.recipeImage,
                            contentDescription = "image de la recette ${order.recipeName}",
                            contentScale = ContentScale.Crop
                        )
                    }

                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(
                                text = order.recipeName,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                            Text(order.orderStatus, color = MaterialTheme.colorScheme.secondary)
                            Text(
                                stringResource(R.string.order_qty) + " : ${order.qty}",
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Row {
                            IconButton(
                                modifier = Modifier.size(40.dp),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.error
                                ),
                                onClick = {
                                    onEvent(HomeEvent.OnOrderMarkAsCanceled(order.id, order.userId))
                                }
                            ) {
                                Icon(
                                    Icons.Rounded.Clear,
                                    contentDescription = "mark order as canceled button"
                                )
                            }
                            IconButton(
                                modifier = Modifier.size(40.dp),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                onClick = {
                                    onEvent(HomeEvent.OnOrderMarkAsDelivered(order.id, order.userId))
                                }
                            ) {
                                Icon(
                                    Icons.Rounded.Check,
                                    contentDescription = "mark order as delivered button"
                                )
                            }
                        }
                    }

                }

                if (isOrderDetailOpen) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                            Text(stringResource(R.string.lieu, order.university))
                            Text(
                                stringResource(
                                    R.string.order_type,
                                    if (order.orderType) stringResource(R.string.order_instant) else stringResource(
                                        R.string.order_program
                                    )
                                )
                            )
                            Text(
                                stringResource(
                                    R.string.order_price,
                                    order.qty * order.recipePrice
                                )
                            )
                        }
                    }

            }

        }
    }
}

@PreviewLightDark
@Composable
fun HomePagePreview(modifier: Modifier = Modifier) {
    YummyFoodsAdminTheme {
        Surface {
            Scaffold { innerP ->
                HomeBody(
                    rememberNavController(),
                    HomeState(
                        orders = (1..12).map { order }
                    ),
                    onEvent = {}
                )
            }
        }
    }
}

internal val order = Order(
    id = "",
    orderType = true,
    orderStatus = "En attente",
    userId = "",
    recipeId = "",
    phoneNumber = "+243826861073",
    qty = 3,
    recipeName = "Pizza",
    recipePrice = 3.2,
    university = "Universite de kinshasa",
    recipeImage = ""
)