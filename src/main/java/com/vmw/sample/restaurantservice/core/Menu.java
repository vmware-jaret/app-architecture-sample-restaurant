package com.vmw.sample.restaurantservice.core;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class Menu {
    private final String restaurant;
    private final List<String> meals;

    public Menu(final String restaurant, List<String> meals) {
        this.restaurant = restaurant;
        this.meals = meals;
    }

    public String getRestaurant() {
        return this.restaurant;
    }

    public List<String> getMeals() {
        return this.meals;
    }
}
