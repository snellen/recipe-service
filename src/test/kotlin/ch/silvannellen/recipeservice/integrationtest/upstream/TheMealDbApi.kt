package ch.silvannellen.recipeservice.integrationtest.upstream

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestComponent
import org.springframework.stereotype.Component

/**
 * Helper class to configure the given WireMockServer to return stubs for various paths in the Meal DB API.
 */
@Component
class TheMealDbApi @Autowired constructor(
        val mockServer: WireMockServer,
        @Value("\${themealdb.apikey}")
        private var mealDBMockApiKey: String
) {
    /**
     * Mock a random recipe (/api/json/v1/< API key>/random.php)
     */
    fun mockRandomRecipeV1(bodyFileName: String) {
        mockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/api/json/v1/$mealDBMockApiKey/random.php"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("themealdb/v1/$bodyFileName.json")))
    }

}