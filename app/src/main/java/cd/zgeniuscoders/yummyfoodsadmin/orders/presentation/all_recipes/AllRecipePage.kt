package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.components.RecipeItem
import cd.zgeniuscoders.yummyfoodsadmin.ui.theme.YummyFoodsAdminTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllRecipePage(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    val vm = koinViewModel<AllRecipeViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()

    val onEvent = vm::onTriggerEvent

    LaunchedEffect(state.flashMessage) {
        if (state.flashMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(state.flashMessage)
        }
    }

    AllRecipeBody(
        state,
        onEvent,
        navHostController
    )

}

@Composable
fun AllRecipeBody(
    state: AllRecipeState,
    onEvent: (event: AllRecipeEvent) -> Unit,
    navHostController: NavHostController
) {

    when {
        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.recipes) { recipe ->
                    RecipeItem(
                        data = recipe,
                        onDelete = {
                            onEvent(AllRecipeEvent.OnDeleteRecipe(it))
                        },
                        onUpdate = {}
                    )
                }
            }
        }
    }


}

@PreviewLightDark
@Composable
fun AllRecipePagePreview(modifier: Modifier = Modifier) {
    YummyFoodsAdminTheme {
        Surface {
            AllRecipeBody(
                state = AllRecipeState(),
                onEvent = {},
                navHostController = rememberNavController(),
            )
        }
    }
}