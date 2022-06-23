package com.vmw.sample.restaurantservice.core;

import java.util.List;

public interface RestaurantApplicationPort {

    /**
     * Register a new meal the restaurant could prepare
     */
    void registerMeal(String restaurant, String name);

    /**
     * Returns the menu of every Restaurant
     * @return
     */
    List<RestaurantMenu> retrieveMenu();
}
