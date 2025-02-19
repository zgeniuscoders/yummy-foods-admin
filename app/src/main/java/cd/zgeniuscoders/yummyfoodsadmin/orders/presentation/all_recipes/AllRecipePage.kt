package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllRecipePage(modifier: Modifier = Modifier) {

    val vm = koinViewModel<AllRecipeViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()

    val onEvent = vm::onTriggerEvent

}