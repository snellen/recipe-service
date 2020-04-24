package ch.silvannellen.recipeservice.controller

import ch.silvannellen.recipeservice.api.RecipeDTO
import ch.silvannellen.recipeservice.controller.mapping.RecipeToDTOMapper
import ch.silvannellen.recipeservice.service.RecipeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.constraints.NotBlank

@RestController
class RecipeController(
        private val recipeService: RecipeService,
        private val recipeToDTOMapper: RecipeToDTOMapper
) {

    @GetMapping("recipe/{id}")
    fun getRecipe(@PathVariable @NotBlank id: String): Mono<RecipeDTO> {
        return recipeService.getRecipe(id).map { recipeToDTOMapper.map(it) }
    }

    @GetMapping("recipe/random")
    fun getRandomRecipe(): Mono<RecipeDTO> {
        return recipeService.getRandomRecipe().map { recipeToDTOMapper.map(it) }
    }
}