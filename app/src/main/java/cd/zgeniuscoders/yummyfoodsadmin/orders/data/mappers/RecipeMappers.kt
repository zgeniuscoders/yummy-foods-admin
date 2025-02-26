package cd.zgeniuscoders.yummyfoodsadmin.orders.data.mappers

import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipeDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.data.dto.RecipesDto
import cd.zgeniuscoders.yummyfoodsadmin.orders.domain.models.Recipe

fun RecipesDto.toRecipeList(): List<Recipe> {
    return data.map {
        Recipe(
            it.id,
            it.name,
            it.category,
            it.description,
            it.price,
            it.recipePhoto
        )
    }
}

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(data.id, data.name, data.category, data.description, data.price, data.recipePhoto)
}