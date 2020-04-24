package ch.silvannellen.recipeservice.service

import ch.silvannellen.recipeservice.model.Recipe
import ch.silvannellen.recipeservice.repository.themealdb.TheMealDbRepository
import ch.silvannellen.recipeservice.service.mappers.themealdb.v1.MealDTOToRecipeMapper
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
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

    fun getRecipe(prefixedId: String): Mono<Recipe> {
        if(prefixedId.startsWith(THE_MEAL_DB_ID_PREFIX)) {
            return theMealDbRepository
                    .getRecipeV1(prefixedId.removePrefix(THE_MEAL_DB_ID_PREFIX))
                    .map { mealDTOToRecipeMapper.map(it) { id -> "$THE_MEAL_DB_ID_PREFIX$id" } }
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with id $prefixedId not found")
        }
    }
}