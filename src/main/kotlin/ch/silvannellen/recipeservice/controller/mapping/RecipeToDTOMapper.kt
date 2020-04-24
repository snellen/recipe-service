package ch.silvannellen.recipeservice.controller.mapping

import ch.silvannellen.recipeservice.api.IngredientDTO
import ch.silvannellen.recipeservice.api.RecipeDTO
import ch.silvannellen.recipeservice.model.Recipe
import org.springframework.stereotype.Component

@Component
class RecipeToDTOMapper {
    fun map(recipe: Recipe) : RecipeDTO {
        return RecipeDTO(
                recipe.id,
                recipe.name,
                recipe.ingredients.map { IngredientDTO(it.name, it.measure) },
                recipe.method,
                recipe.picture
        )
    }
}