package com.vmw.sample.restaurantservice.core

import spock.lang.Specification

class RestaurantSpec extends Specification {

    def "Given a Restaurant without any Meals yet, when the owner register the Meal it gets part of the Menu"() {
        given:
            def subject = new Restaurant("New York")
        when:
            subject.registerMeal("italian-spaghetti")
        then:
            subject.getMenu() == ["italian-spaghetti"]
    }

    def "Given a Restaurant with two registered Meals, when the owner register the same Meal again it gets rejected"() {
        given:
            def subject = new Restaurant("Washington DC", ["italian-spaghetti", "couscous"])
        when:
            subject.registerMeal("italian-spaghetti")
        then:
            thrown(MealAlreadyRegisteredException)
    }

    def "Given a Restaurant with two registered Meals, when the customer requests the Menu it get all the Meals registered"() {
        given:
            def subject = new Restaurant("Washington DC", ["italian-spaghetti", "couscous"])
        when:
            List<String> result = subject.getMenu();
        then:
            result == ["italian-spaghetti", "couscous"]
    }
}
