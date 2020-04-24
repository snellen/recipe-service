package ch.silvannellen.recipeservice.service.mappers.themealdb.v1

import ch.silvannellen.recipeservice.model.Ingredient
import ch.silvannellen.recipeservice.model.Recipe
import ch.silvannellen.recipeservice.repository.themealdb.v1.MealDTO
import org.springframework.stereotype.Component
import java.net.URI

@Component
class MealDTOToRecipeMapper {
    fun map(meal: MealDTO, idTransformation: ((String) -> String) = { it }) = Recipe(
            idTransformation(meal.id.toString()),
            meal.name,
            extractIngredients(meal),
            extractMethod(meal),
            URI(meal.thumbnail)
    )

    private fun extractMethod(meal: MealDTO) =
    // For some meals, the instructions are delimited by "\r\n\r\n",
            // for others with just "\r\n"...
            if (meal.instructions.contains("\r\n\r\n")) {
                meal.instructions.split("\r\n\r\n")
            } else {
                meal.instructions.split("\r\n")
            }


    private fun extractIngredients(meal: MealDTO): Collection<Ingredient> {
        return listOf(
                createIngredient(meal.ingredient1, meal.measure1),
                createIngredient(meal.ingredient2, meal.measure2),
                createIngredient(meal.ingredient3, meal.measure3),
                createIngredient(meal.ingredient4, meal.measure4),
                createIngredient(meal.ingredient5, meal.measure5),
                createIngredient(meal.ingredient6, meal.measure6),
                createIngredient(meal.ingredient7, meal.measure7),
                createIngredient(meal.ingredient8, meal.measure8),
                createIngredient(meal.ingredient9, meal.measure9),
                createIngredient(meal.ingredient10, meal.measure10),
                createIngredient(meal.ingredient11, meal.measure11),
                createIngredient(meal.ingredient12, meal.measure12),
                createIngredient(meal.ingredient13, meal.measure13),
                createIngredient(meal.ingredient14, meal.measure14),
                createIngredient(meal.ingredient15, meal.measure15),
                createIngredient(meal.ingredient16, meal.measure16),
                createIngredient(meal.ingredient17, meal.measure17),
                createIngredient(meal.ingredient18, meal.measure18),
                createIngredient(meal.ingredient19, meal.measure19),
                createIngredient(meal.ingredient20, meal.measure20)
        ).mapNotNull { it }
    }

    fun createIngredient(ingredient: String?, measure: String?) =
            if (!ingredient.isNullOrBlank() && !measure.isNullOrBlank()) {
                Ingredient(ingredient, measure)
            } else {
                null
            }
}