package com.vmw.sample.restaurantservice.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MealPreparement {

    private final String restaurant;

    public MealPreparement(String restaurant) {
        this.restaurant = restaurant;
    }
    //private Map<String, List<String>> mealsToBePrepared = new HashMap<>();
    private List<String> mealsToBePrepared = new ArrayList<>();

    /**
     * Register a meal that needs to be prepared by the given restaurant
     */
    public void register(String meal) {
        this.mealsToBePrepared.add(meal);
    }

    public void deregister(String meal) {
        this.mealsToBePrepared.remove(meal);
    }

    public List<String> getOverview() {
        return Collections.unmodifiableList(this.mealsToBePrepared);
    }
}
