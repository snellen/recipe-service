package ch.silvannellen.recipeservice.service

import ch.silvannellen.recipeservice.model.Recipe
import ch.silvannellen.recipeservice.repository.themealdb.TheMealDbRepository
import ch.silvannellen.recipeservice.service.mappers.themealdb.v1.MealDTOToRecipeMapper
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

private const val THE_MEAL_DB_ID_PREFIX = "tmd"

@Service
class RecipeService(
        private val theMealDbRepository: TheMealDbRepository,
        private val mealDTOToRecipeMapper: MealDTOToRecipeMapper
) {
    fun getRandomRecipe(): Mono<Recipe> {
        return theMealDbRepository
                .getRandomRecipeV1()
                .map { mealDTOToRecipeMapper.map(it) { id -> "$THE_MEAL_DB_ID_PREFIX$id" } }
    }
}