package com.vmw.sample.restaurantservice.core;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Restaurant {
    private final String name;
    private final List<String> meals = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, List<String> meals) {
        this.name = name;
        this.meals.addAll(meals);
    }

    public void registerMeal(String meal) {
        if (this.meals.contains(meal)) {
            throw new MealAlreadyRegisteredException();
        }
        this.meals.add(meal);
    }

    public String getName() {
        return this.name;
    }

    public List<String> getMenu() {
        return this.meals;
    }
}
