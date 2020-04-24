package ch.silvannellen.recipeservice.repository.themealdb

import ch.silvannellen.recipeservice.repository.themealdb.v1.MealDTO
import ch.silvannellen.recipeservice.repository.themealdb.v1.MealListDTO
import io.netty.channel.ChannelOption
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient

@Repository
class TheMealDbRepository(
        @Value("\${themealdb.protocol}") protocol: String,
        @Value("\${themealdb.host}") host: String,
        @Value("\${themealdb.port}") port: String,
        @Value("\${themealdb.basepath}") basepath: String,
        @Value("\${themealdb.apikey}") apiKey: String
) {
    private val webClient: WebClient = WebClient.builder()
            .baseUrl("$protocol://$host:$port/$basepath/$apiKey/")
            .clientConnector(ReactorClientHttpConnector(HttpClient.from(
                    TcpClient.create()
                            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            ).wiretap(true)))
            .build()

    fun getRandomRecipeV1(): Mono<MealDTO> {
        return webClient
                .get()
                .uri("random.php")
                .retrieve()
                .bodyToMono(MealListDTO::class.java)
                .map { it.meals.first() }
    }

    fun getRecipeV1(id: String): Mono<MealDTO> {
        return webClient
                .get()
                .uri() {
                    it.path("lookup.php")
                            .queryParam("i", id)
                            .build()
                }
                .retrieve()
                .bodyToMono(MealListDTO::class.java)
                .map { it.meals.first() }
    }
}