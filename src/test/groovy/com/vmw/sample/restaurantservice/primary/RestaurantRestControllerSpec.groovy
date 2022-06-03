package com.vmw.sample.restaurantservice.primary

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(value = RestaurantRestController.class)
class RestaurantRestControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestaurantApplicationPort restaurantApplicationPort;

    def "Given a POST for registering a meal, we expect that it is stored and new identifier has been returned"() {
        given:
            def body = readFile("RegisterMealRequest01.json")
        when:
            def results = mvc.perform(post('/api/restaurant/v1/register-meal')
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
        then:
            1 * restaurantApplicationPort.registerMeal("italian spaghetti") >> CompletableFuture.completedFuture("test-id")
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