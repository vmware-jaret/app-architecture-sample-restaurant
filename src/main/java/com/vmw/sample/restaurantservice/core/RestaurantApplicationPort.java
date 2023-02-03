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

    /**
     * Returns the current meals to be prepared by given Restaurant
     * @return
     */
    List<String> retrievePreparementOverview(String restaurant);

    /**
     * Set the meal status to prepared, e.g. driver could fulfill delivery
     */
    void mealPrepared(String restaurant, String name);
}
