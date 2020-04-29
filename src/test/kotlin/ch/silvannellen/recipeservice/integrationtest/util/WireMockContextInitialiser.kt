package ch.silvannellen.recipeservice.integrationtest.util

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent

class WireMockContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        with(WireMockServer(WireMockConfiguration()
                .dynamicPort())) {
            start()
            applicationContext.beanFactory.registerSingleton("mockServer", this)
            applicationContext.addApplicationListener {
                if (it is ContextClosedEvent) {
                    stop()
                }
            }

            TestPropertyValues
                    .of(
                            "themealdb.port=${port()}",
                            "themealdb.host=localhost"
                    ).applyTo(applicationContext)
        }
    }
}