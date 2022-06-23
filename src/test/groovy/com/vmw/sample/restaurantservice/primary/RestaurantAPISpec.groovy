package com.vmw.sample.restaurantservice.primary

import com.vmw.sample.restaurantservice.core.RestaurantMenu
import com.vmw.sample.restaurantservice.core.RestaurantApplicationPort
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import java.nio.charset.StandardCharsets
import java.util.concurrent.CompletableFuture

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(value = RestaurantAPI.class)
class RestaurantAPISpec extends Specification {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestaurantApplicationPort restaurantApplicationPort

    def "Given two restaurants with both a menu, when submitting a HTTP GET for retrieving the menu, we expect the whole menu returned"() {
        given:
            def menus = [
                    new RestaurantMenu("Restaurant New York", ["italian-spaghetti", "pizza"]),
                    new RestaurantMenu("Restaurant Washington-DC", ["italian-macaroni"]),
            ]

        when:
            def result = mvc.perform(get('/api/restaurant/v1/retrieve-menu'))
        then:
            1 * restaurantApplicationPort.retrieveMenu() >> menus
            result.andExpect(status().isOk())
            result.andExpect(jsonPath("\$.menus.[0].restaurant").value("Restaurant New York"))
            result.andExpect(jsonPath("\$.menus.[0].meals").value(["italian-spaghetti", "pizza"]))
            result.andExpect(jsonPath("\$.menus.[1].restaurant").value("Restaurant Washington-DC"))
            result.andExpect(jsonPath("\$.menus.[1].meals").value(["italian-macaroni"]))
    }

    def "Given a POST for registering a meal, we expect that it is stored and new identifier has been returned"() {
        given:
            def body = """{
                "restaurant": "Washington-DC",
                "meal": "italian spaghetti"
            }"""
            println(body)
        when:
            def results = mvc.perform(post('/api/restaurant/v1/register-meal')
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
        then:
            1 * restaurantApplicationPort.registerMeal("Washington-DC", "italian spaghetti")
            results.andExpect(status().isOk())
    }

    String readFile(String fileName) throws IOException {
        return IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream(fileName)), StandardCharsets.UTF_8)
    }

    @TestConfiguration
    static class Config {

        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        RestaurantApplicationPort restaurantApplicationPort() {
            return detachedMockFactory.Mock(RestaurantApplicationPort)
        }
    }
}
