package com.vmw.sample.restaurantservice.secondary

import com.vmw.sample.restaurantservice.core.Restaurant
import com.vmw.sample.restaurantservice.core.RestaurantNotFoundException
import spock.lang.Specification

import java.util.stream.Collectors

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

    def "Given a repository having one Restaurant, when we get it by id we expect that the RestaurantEntity is loaded and transformed to the Aggregate"() {
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
            def result = subject.getById("New York")
        then:
            result == new Restaurant("New York", ["pizza"])
            1 * repositoryMock.findById("New York") >> Optional.of(restaurant)
    }

    def "Given a repository, when we get it by id not existing, we expect RestaurantNotFoundException"() {
        when:
            subject.getById("New York")
        then:
            thrown(RestaurantNotFoundException)
            1 * repositoryMock.findById("New York") >> Optional.empty()
    }

    def "Given an Restaurant, when we persist it, we expect that it is translated to the RestaurantEntity and persisted"() {
        given:
            def restaurant = new Restaurant("New York,", ["pizza salami"])
        when:
            subject.persist(restaurant)
        then:
            1 * repositoryMock.save(createRestaurantEntity("New York", ["pizza salami"]))
    }

    RestaurantEntity createRestaurantEntity(String name, List<String> meals) {
        def restaurant = new RestaurantEntity()
        def id = new RestaurantId()
        id.name = name
        restaurant.id = id

        restaurant.meals = meals.stream().map(meal -> {
            def mealEntity = new MealEntity()
            def mealId = new MealId()
            mealId.restaurantId = id
            mealId.name = "pizza"
            mealEntity.id = mealId
            return mealEntity
        }).collect(Collectors.toList())

        return restaurant
    }
}
