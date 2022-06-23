package com.vmw.sample.restaurantservice.core

import spock.lang.Specification

class RestaurantApplicationServiceSpec extends Specification {

    def restaurantRepositoryPort = Mock(RestaurantRepositoryPort)

    private RestaurantApplicationService subject =
            new RestaurantApplicationService(restaurantRepositoryPort)

    def "When retrieving the menu we expect the menu is extracted from all Restaurants available in the repository"() {
        given:
            def restaurantOne = new Restaurant("New York", ["pizza"])
            def restaurantTwo = new Restaurant("Washington DC", ["spaghetti"])
        when:
            def result = subject.retrieveMenu()
        then:
            result == [
                    new RestaurantMenu("New York", ["pizza"]),
                    new RestaurantMenu("Washington DC", ["spaghetti"])
            ]
            1 * restaurantRepositoryPort.getAll() >> [restaurantOne, restaurantTwo]
    }

    def "When registering a meal we expect the Restaurant aggregate is loaded, meal is added and new Restaurant is persisted."() {
        given:
            def restaurant = "Washington DC"
            def name = "italian-spaghetti"
        when:
            subject.registerMeal(restaurant, name)
        then:
            1 * restaurantRepositoryPort.getById("Washington DC") >> new Restaurant("Washington DC")
            1 * restaurantRepositoryPort.persist(new Restaurant("Washington DC", ["italian-spaghetti"]))
    }

    /*
    @TestConfiguration
    static class Config extends WebSecurityConfigurerAdapter {

        def detachedMockFactory = new DetachedMockFactory()


        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            // Disable CSRF
            httpSecurity.csrf().disable()
            // Permit all requests without authentication
                    .authorizeRequests().anyRequest().permitAll();
        }
    }
    */
}
