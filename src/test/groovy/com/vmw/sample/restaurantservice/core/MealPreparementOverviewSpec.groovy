package com.vmw.sample.restaurantservice.core

import spock.lang.Specification

class MealPreparementOverviewSpec extends Specification {

    def "Given no meals to prepare for a Restaurant, when the owner retrieve the overview it gets an empty overview"() {
        given:
            def subject = new MealPreparement("New York")
        when:
            List<String> result = subject.getOverview()
        then:
            result == []
    }

    def "Given a Restaurant with two meals to be prepared, when the owner retrieve the overview it gets those two meals"() {
        given:
            def subject = new MealPreparement("New York")
            subject.register("italian-spaghetti")
            subject.register("italian-macaroni")
        when:
            List<String> result = subject.getOverview()
        then:
            result == ["italian-spaghetti", "italian-macaroni"]
    }

    def "Given a Restaurant with one meal to be prepared, when owner prepared the meal it won't be in the overview anymore."() {
        given:
            def subject = new MealPreparement("New York")
            subject.register("italian-spaghetti")
        when:
            subject.deregister("italian-spaghetti")
        then:
            subject.getOverview() == []
    }
}
