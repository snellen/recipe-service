package ch.silvannellen.recipeservice.repository.themealdb.v1

import com.fasterxml.jackson.annotation.JsonProperty

data class MealListDTO(
        @JsonProperty("meals")
        val meals: Collection<MealDTO>
)