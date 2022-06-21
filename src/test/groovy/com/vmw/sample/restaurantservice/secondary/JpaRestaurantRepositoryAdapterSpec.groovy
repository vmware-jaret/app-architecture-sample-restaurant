package com.vmw.sample.restaurantservice.secondary

import com.vmw.sample.restaurantservice.core.Restaurant
import spock.lang.Specification

class JpaRestaurantRepositoryAdapterSpec extends Specification {
    def repositoryMock = Mock(RestaurantRepository)
    def subject = new JpaRestaurantRepositoryAdapter(repositoryMock)

    def "Given a repository having one Restaurant, when we retrieve all we expect that the RestaurantEntity is loaded and transformed to the Aggregate"() {
        given:
            def restaurant = new RestaurantEntity()
            def id = new RestaurantId()
            id.name = "New York"
            restaurant.id = id

            def meal = new MealEntity()
            def mealId = new MealId()
            mealId.restaurantId = id
            mealId.name = "pizza"
            meal.id = mealId

            restaurant.meals = Collections.singleton(meal)
        when:
            def result = subject.getAll()
        then:
            result == [
                    new Restaurant("New York", ["pizza"])
            ]
            1 * repositoryMock.findAll() >> [restaurant]
    }
}
