package ch.silvannellen.recipeservice.repository.themealdb.v1

import com.fasterxml.jackson.annotation.JsonProperty

data class MealDTO(
        @JsonProperty("idMeal")
        val id: Long,
        @JsonProperty("strMeal")
        val name: String,
        @JsonProperty("strMealThumb")
        val thumbnail: String,
        @JsonProperty("strInstructions")
        val instructions: String,
        @JsonProperty("strIngredient1")
        val ingredient1: String?,
        @JsonProperty("strIngredient2")
        val ingredient2: String?,
        @JsonProperty("strIngredient3")
        val ingredient3: String?,
        @JsonProperty("strIngredient4")
        val ingredient4: String?,
        @JsonProperty("strIngredient5")
        val ingredient5: String?,
        @JsonProperty("strIngredient6")
        val ingredient6: String?,
        @JsonProperty("strIngredient7")
        val ingredient7: String?,
        @JsonProperty("strIngredient8")
        val ingredient8: String?,
        @JsonProperty("strIngredient9")
        val ingredient9: String?,
        @JsonProperty("strIngredient10")
        val ingredient10: String?,
        @JsonProperty("strIngredient11")
        val ingredient11: String?,
        @JsonProperty("strIngredient12")
        val ingredient12: String?,
        @JsonProperty("strIngredient13")
        val ingredient13: String?,
        @JsonProperty("strIngredient14")
        val ingredient14: String?,
        @JsonProperty("strIngredient15")
        val ingredient15: String?,
        @JsonProperty("strIngredient16")
        val ingredient16: String?,
        @JsonProperty("strIngredient17")
        val ingredient17: String?,
        @JsonProperty("strIngredient18")
        val ingredient18: String?,
        @JsonProperty("strIngredient19")
        val ingredient19: String?,
        @JsonProperty("strIngredient20")
        val ingredient20: String?,
        @JsonProperty("strMeasure1")
        val measure1: String?,
        @JsonProperty("strMeasure2")
        val measure2: String?,
        @JsonProperty("strMeasure3")
        val measure3: String?,
        @JsonProperty("strMeasure4")
        val measure4: String?,
        @JsonProperty("strMeasure5")
        val measure5: String?,
        @JsonProperty("strMeasure6")
        val measure6: String?,
        @JsonProperty("strMeasure7")
        val measure7: String?,
        @JsonProperty("strMeasure8")
        val measure8: String?,
        @JsonProperty("strMeasure9")
        val measure9: String?,
        @JsonProperty("strMeasure10")
        val measure10: String?,
        @JsonProperty("strMeasure11")
        val measure11: String?,
        @JsonProperty("strMeasure12")
        val measure12: String?,
        @JsonProperty("strMeasure13")
        val measure13: String?,
        @JsonProperty("strMeasure14")
        val measure14: String?,
        @JsonProperty("strMeasure15")
        val measure15: String?,
        @JsonProperty("strMeasure16")
        val measure16: String?,
        @JsonProperty("strMeasure17")
        val measure17: String?,
        @JsonProperty("strMeasure18")
        val measure18: String?,
        @JsonProperty("strMeasure19")
        val measure19: String?,
        @JsonProperty("strMeasure20")
        val measure20: String?
)