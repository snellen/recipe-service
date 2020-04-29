package ch.silvannellen.recipeservice.integrationtest

import ch.silvannellen.recipeservice.RecipeServiceApplication
import ch.silvannellen.recipeservice.integrationtest.upstream.TheMealDbApi
import ch.silvannellen.recipeservice.integrationtest.util.WireMockContextInitializer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(classes = [RecipeServiceApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@ContextConfiguration(initializers = [WireMockContextInitializer::class])
class GetRandomRecipeHappyPathTest {

    @Autowired
    private lateinit var theMealDbApi: TheMealDbApi

    @Autowired
    private lateinit var webClient: WebTestClient

    @AfterEach
    fun resetMockServer() {
        theMealDbApi.mockServer.resetAll()
    }

    @Test
    fun givenValidRequest_validResponseIsReturned() {
        // Given
        theMealDbApi.mockRandomRecipeV1("random-recipe-1")

        // When
        val response = webClient
                .get()
                .uri("recipe/random")
                .exchange()

        // Then
        response
                .expectStatus().is2xxSuccessful
                .expectBody()
                .jsonPath("id").isEqualTo("tmd52877")
                .jsonPath("name").isEqualTo("Lamb and Potato pie")
                .jsonPath("ingredients.length()").isEqualTo(9)
                .jsonPath("ingredients[0].name").isEqualTo("Lamb Shoulder")
                .jsonPath("ingredients[0].measure").isEqualTo("500g")
                .jsonPath("ingredients[1].name").isEqualTo("Flour")
                .jsonPath("ingredients[1].measure").isEqualTo("1 tbls")
                .jsonPath("ingredients[2].name").isEqualTo("Vegetable Oil")
                .jsonPath("ingredients[2].measure").isEqualTo("Dash")
                .jsonPath("ingredients[3].name").isEqualTo("Onion")
                .jsonPath("ingredients[3].measure").isEqualTo("1 sliced")
                .jsonPath("ingredients[4].name").isEqualTo("Carrots")
                .jsonPath("ingredients[4].measure").isEqualTo("2 sliced")
                .jsonPath("ingredients[5].name").isEqualTo("Vegetable Stock")
                .jsonPath("ingredients[5].measure").isEqualTo("350ml/12fl")
                .jsonPath("ingredients[6].name").isEqualTo("Potatoes")
                .jsonPath("ingredients[6].measure").isEqualTo("500g")
                .jsonPath("ingredients[7].name").isEqualTo("Shortcrust Pastry")
                .jsonPath("ingredients[7].measure").isEqualTo("450g")
                .jsonPath("ingredients[8].name").isEqualTo("Eggs")
                .jsonPath("ingredients[8].measure").isEqualTo("To Glaze")
                .jsonPath("method.length()").isEqualTo(9)
                .jsonPath("method[0]").isEqualTo("Dust the meat with flour to lightly coat.")
                .jsonPath("method[1]").isEqualTo("Heat enough vegetable oil in a large saucepan to fill the base, and fry the onion and meat until lightly browned. Season with salt and pepper.")
                .jsonPath("method[2]").isEqualTo("Add the carrots, stock and more seasoning to taste.")
                .jsonPath("method[3]").isEqualTo("Bring to the boil, cover and reduce the heat to a simmer. Simmer for at least an hour or until the meat is tender. Take your time cooking the meat, the longer you leave it to cook, the better the flavour will be.")
                .jsonPath("method[4]").isEqualTo("Preheat the oven to 180C/350F/Gas 4.")
                .jsonPath("method[5]").isEqualTo("Add the drained potato cubes to the lamb.")
                .jsonPath("method[6]").isEqualTo("Turn the mixture into a pie dish or casserole and cover with the shortcrust pastry. Make three slits in the top of the pastry to release any steam while cooking.")
                .jsonPath("method[7]").isEqualTo("Brush with beaten egg and bake for about 40 minutes, until the pastry is golden brown.")
                .jsonPath("method[8]").isEqualTo("Serve.")
    }
}