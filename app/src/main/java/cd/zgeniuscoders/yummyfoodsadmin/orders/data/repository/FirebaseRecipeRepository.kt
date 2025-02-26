package cd.zgeniuscoders.yummyfoodsadmin.orders.data.repository

import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipeDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipesDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipesDtoData
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.repository.RecipeRepository
import cd.zgeniuscoders.yummyfoodsadmin.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseRecipeRepository(
    private val db: FirebaseFirestore
) : RecipeRepository {

    private val collection = db.collection("recipes")
    override suspend fun deleteRecipe(recipeId: String): Flow<Resource<Boolean>> = callbackFlow {
        try {
            collection
                .document(recipeId)
                .delete()

            trySend(
                Resource.Success(true)
            )
        } catch (e: Exception) {
            trySend(
                Resource.Error(e.message.toString())
            )
        }
        awaitClose()
    }

    override suspend fun getRecipes(): Flow<Resource<RecipesDto>> = callbackFlow {

        collection
            .addSnapshotListener { value, error ->

                if (error != null) {
                    trySend(
                        Resource.Error(
                            message = error.message.toString()
                        )
                    )
                }

                if (value != null) {
                    val recipes = value.toObjects(RecipesDtoData::class.java)

                    trySend(
                        Resource.Success(
                            data = RecipesDto(
                                data = recipes
                            )
                        )
                    )
                }
            }

        awaitClose()

    }

    override suspend fun getDrinks(): Flow<Resource<RecipesDto>> = callbackFlow {
        collection
            .whereEqualTo("category", "boissons")
            .addSnapshotListener { value, error ->

                if (error != null) {
                    trySend(
                        Resource.Error(
                            message = error.message.toString()
                        )
                    )
                }

                if (value != null) {
                    val recipes = value.toObjects(RecipesDtoData::class.java)

                    trySend(
                        Resource.Success(
                            data = RecipesDto(
                                data = recipes
                            )
                        )
                    )
                }
            }

        awaitClose()
    }

    override suspend fun getRecipe(id: String): Flow<Resource<RecipeDto>> = callbackFlow {
        collection
            .document(id).addSnapshotListener { value, error ->
                if (error != null) {
                    trySend(
                        Resource.Error(
                            message = error.message.toString()
                        )
                    )
                }

                if (value != null) {
                    val recipe = value.toObject(RecipesDtoData::class.java)

                    recipe?.let {
                        trySend(
                            Resource.Success(
                                data = RecipeDto(
                                    data = it
                                )
                            )
                        )
                    }

                }
            }

        awaitClose()
    }
}