package com.vmw.sample.restaurantservice.core;

public interface MenuRepositoryPort {

    /**
     * Persist meal
     * @param name of meal
     * @return identifier
     */
    String persistMeal(String name);
}
