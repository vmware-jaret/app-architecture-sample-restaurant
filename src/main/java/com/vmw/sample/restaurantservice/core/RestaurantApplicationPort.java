package com.vmw.sample.restaurantservice.core;

import java.util.concurrent.CompletableFuture;

public interface RestaurantApplicationPort {

    /**
     * Register a new meal the restaurants could prepare
     * @return new unique identifier over the set of meals known
     */
    CompletableFuture<String> registerMeal(String name);
}
