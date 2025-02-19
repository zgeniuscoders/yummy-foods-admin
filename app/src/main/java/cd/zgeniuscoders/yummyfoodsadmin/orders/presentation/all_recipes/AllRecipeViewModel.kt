package cd.zgeniuscoders.yummyfoodsadmin.orders.presentation.all_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class AllRecipeViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {


    private val _state = MutableStateFlow(AllRecipeState())
    val state = _state
        .onStart {
            getRecipes()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            _state.value
        )


    fun onTriggerEvent(event: AllRecipeEvent) {
        when (event) {
            is AllRecipeEvent.OnDeleteRecipe -> deleteRecipe(event.recipeId)
        }
    }

    private fun getRecipes() {
        viewModelScope.launch {
            recipeRepository
                .getRecipes()
                .onEach { res ->
                    when (res) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(flashMessage = res.message.toString())
                            }
                        }

                        is Resource.Success -> {

                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun deleteRecipe(recipeId: String) {
        viewModelScope.launch {

            _state.update {
                it.copy(flashMessage = "")
            }

            recipeRepository
                .deleteRecipe(recipeId)
                .onEach { res ->
                    when (res) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(flashMessage = res.message.toString())
                            }
                        }

                        is Resource.Success -> {
                            _state.update {
                                it.copy(flashMessage = "suppression effectuer avec success")
                            }
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}