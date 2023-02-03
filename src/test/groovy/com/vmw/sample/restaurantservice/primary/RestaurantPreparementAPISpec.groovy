package com.vmw.sample.restaurantservice.primary

import com.vmw.sample.restaurantservice.core.RestaurantApplicationPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(value = RestaurantPreparementAPI.class)
class RestaurantPreparementAPISpec extends Specification {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestaurantApplicationPort restaurantApplicationPort

    def "When submitting a HTTP GET for retrieving the overview, we expect the overview for the given restaurant returned"() {
        given:
            def meals = ["italian-spaghetti", "italian-macaroni"]
        when:
            def result = mvc.perform(get('/api/restaurant/v1/preparement/New York'))
        then:
            1 * restaurantApplicationPort.retrievePreparementOverview("New York") >> meals
            result.andExpect(status().isOk())
            result.andExpect(jsonPath("\$.meals").value(["italian-spaghetti", "italian-macaroni"]))
    }

    def "When submitting a HTTP POST that meal is prepared, we expect that it is delegated to the system"() {
        given:
            def body = """{
                "restaurant": "Washington-DC",
                "meal": "italian spaghetti"
            }"""
        when:
            def results = mvc.perform(post('/api/restaurant/v1/meal-prepared')
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
        then:
            1 * restaurantApplicationPort.mealPrepared("Washington-DC", "italian spaghetti")
            results.andExpect(status().isOk())
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
