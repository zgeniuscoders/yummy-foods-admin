package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository

import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipeDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipesDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Recipe
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun addRecipe(recipe: Recipe): Flow<Resource<Boolean>>

    suspend fun deleteRecipe(recipeId: String): Flow<Resource<Boolean>>

    suspend fun getRecipes(): Flow<Resource<RecipesDto>>

    suspend fun getRecipe(id: String): Flow<Resource<RecipeDto>>

}