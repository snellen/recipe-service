package ch.silvannellen.recipeservice.api

import java.net.URI

data class RecipeDTO(val id: String,
                      val name: String,
                      val ingredients: Collection<IngredientDTO>,
                      val method: Collection<String>,
                      val picture: URI)