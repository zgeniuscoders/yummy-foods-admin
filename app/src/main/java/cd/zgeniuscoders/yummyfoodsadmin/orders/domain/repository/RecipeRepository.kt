package cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository

import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipeDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipesDto
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipes(): Flow<Resource<RecipesDto>>

    suspend fun getDrinks(): Flow<Resource<RecipesDto>>

    suspend fun getRecipe(id: String): Flow<Resource<RecipeDto>>

}