package com.vmw.sample.restaurantservice.secondary

import spock.lang.Specification

class JpaMenuRepositoryAdapterSpec extends Specification {
    def menuRepositoryMock = Mock(MenuRepository)
    def subject = new JpaMenuRepositoryAdapter(menuRepositoryMock)

    def "When persisting a new meal name we expect that the entity is saved and ID is returned"() {
        given:
            def name = "italian spaghetti"
        when:
            def result = subject.persistMeal(name)
        then:
            1 * menuRepositoryMock.save({ it.getName() == "italian spaghetti" }) >> createEntity(5L)
            result == "5"
    }

    def createEntity(Long id) {
        return MealEntity.builder().id(id).build()
    }
}
