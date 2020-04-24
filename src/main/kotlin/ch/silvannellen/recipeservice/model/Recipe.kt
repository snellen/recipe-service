package ch.silvannellen.recipeservice.model

import java.net.URI

data class Recipe(
        val id: String,
        val name: String,
        val ingredients: Collection<Ingredient>,
        val method: Collection<String>,
        val picture: URI
)