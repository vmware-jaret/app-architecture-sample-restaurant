package com.vmw.sample.restaurantservice.core

import spock.lang.Specification

class RestaurantApplicationServiceSpec extends Specification {

    def menuRepositoryPort = Mock(MenuRepositoryPort)
    private RestaurantApplicationService subject =
            new RestaurantApplicationService(menuRepositoryPort)

    def "When registering a meal we expect the meal is persistent and new identifier is returned"() {
        given:
            def name = "italian-spaghetti"
        when:
            def result = subject.registerMeal(name)
        then:
            result.get() == "test-id"
            1 * menuRepositoryPort.persistMeal(name) >> "test-id"
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
