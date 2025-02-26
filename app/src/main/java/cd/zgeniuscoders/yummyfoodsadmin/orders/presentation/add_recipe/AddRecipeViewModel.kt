package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.add_recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Recipe
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository.RecipeRepository
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddRecipeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private var _state = MutableStateFlow(AddRecipeState())

    val state = _state.onStart { }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _state.value
        )

    fun onTriggerEvent(event: AddRecipeEvent) {
        when (event) {
            AddRecipeEvent.OnAddRecipe -> addRecipe()
            is AddRecipeEvent.OnCategoryChange -> _state.update { it.copy(category = event.category) }
            is AddRecipeEvent.OnDescriptionChange -> _state.update { it.copy(description = event.description) }
            is AddRecipeEvent.OnNameChange -> _state.update { it.copy(name = event.name) }
            is AddRecipeEvent.OnPhotoUrlChange -> _state.update { it.copy(recipePhoto = event.url) }
            is AddRecipeEvent.OnPriceChange -> _state.update { it.copy(price = event.price) }
        }
    }

    private fun validated(): Boolean {
        return false;
    }

    private fun addRecipe() {
        viewModelScope.launch {

            _state.update { it.copy(flashMessage = "", isLoading = true) }

            if (validated()) {

                val recipe = Recipe(
                    id = state.value.name,
                    name = state.value.name,
                    description = state.value.description,
                    price = state.value.price.toDouble(),
                    category = state.value.category,
                    recipePhoto = state.value.recipePhoto
                )

                recipeRepository
                    .addRecipe(recipe)
                    .onEach { res ->

                        when (res) {
                            is Resource.Error -> {
                                _state.update {
                                    it.copy(
                                        flashMessage = res.message.toString(),
                                        isLoading = false
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        flashMessage = "Success",
                                        name = "",
                                        description = "",
                                        category = "",
                                        price = "0",
                                        recipePhoto = "",
                                    )
                                }
                            }
                        }

                    }.launchIn(viewModelScope)
            } else {
                _state.update {
                    it.copy(
                        flashMessage = "Veuillez corrige tout les erreur dans le formulaire",
                        isLoading = false
                    )
                }
            }


        }
    }

}