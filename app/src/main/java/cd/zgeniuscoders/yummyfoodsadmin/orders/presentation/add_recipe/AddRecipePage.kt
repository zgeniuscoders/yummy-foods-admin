package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.add_recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddRecipePage(navHostController: NavHostController, snackbarHostState: SnackbarHostState) {
    val vm = koinViewModel<AddRecipeViewModel>()
    val state by vm.state.collectAsStateWithLifecycle()
    val onEvent = vm::onTriggerEvent


    LaunchedEffect(state.flashMessage) {
        if (state.flashMessage.isNotBlank()) {
            snackbarHostState.showSnackbar(state.flashMessage)
        }
    }

    AddRecipePageBody(
        navHostController,
        state,
        onEvent
    )
}

@Composable
fun AddRecipePageBody(
    navHostController: NavHostController,
    state: AddRecipeState,
    onEvent: (event: AddRecipeEvent) -> Unit
) {

    val categories = listOf(
        "boisson",
        "repas"
    )

    val fields = listOf(
        AddRecipeField(
            value = state.name,
            label = "Nom de la rectte",
            placeholder = "Chawarma",
            errorMessage = state.nameError,
            onValueChange = {
                onEvent(AddRecipeEvent.OnNameChange(it))
            }
        ),
        AddRecipeField(
            value = state.description,
            errorMessage = state.descriptionError,
            label = "Description de la rectte",
            placeholder = "Description",
            onValueChange = {
                onEvent(AddRecipeEvent.OnDescriptionChange(it))
            }
        ),
        AddRecipeField(
            value = state.category,
            errorMessage = state.categoryError,
            label = "Categorie de la rectte",
            placeholder = "Boissons",
            onValueChange = {
                onEvent(AddRecipeEvent.OnCategoryChange(it))
            }
        ),
        AddRecipeField(
            errorMessage = state.priceError,
            value = state.price,
            label = "Prix de la rectte",
            placeholder = "12000",
            keyboardType = KeyboardType.Number,
            onValueChange = {
                onEvent(AddRecipeEvent.OnPriceChange(it))
            }
        ),
        AddRecipeField(
            errorMessage = state.recipePhotoError,
            value = state.recipePhoto,
            label = "Image de la recette",
            placeholder = "12000",
            keyboardType = KeyboardType.Uri,
            onValueChange = {
                onEvent(AddRecipeEvent.OnPhotoUrlChange(it))
            }
        )
    )

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(fields) { item ->
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = item.value,
                        isError = item.errorMessage != null,
                        label = {
                            Text(item.label)
                        },
                        placeholder = {
                            Text(item.placeholder)
                        },
                        onValueChange = {
                            item.onValueChange(it)
                        }
                    )
                    if (item.errorMessage != null) {
                        Text(
                            item.errorMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
                item {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onEvent(AddRecipeEvent.OnAddRecipe)
                        }
                    ) {
                        Text("Publier")
                    }
                }
            }
        }
    }

}

data class AddRecipeField(
    val value: String,
    val onValueChange: (value: String) -> Unit,
    val label: String,
    val placeholder: String,
    val errorMessage: String? = null,
    val keyboardType: KeyboardType = KeyboardType.Text
)
