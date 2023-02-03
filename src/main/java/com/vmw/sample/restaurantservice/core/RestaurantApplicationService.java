package com.vmw.sample.restaurantservice.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantApplicationService implements RestaurantApplicationPort {

    private final RestaurantRepositoryPort restaurantRepositoryPort;

    private final MealPreparementRepositoryPort mealPreparementRepositoryPort;

    @Override
    public List<RestaurantMenu> retrieveMenu() {
        final List<Restaurant> restaurants = this.restaurantRepositoryPort.getAll();
        return restaurants.stream()
                .map((restaurant -> new RestaurantMenu(restaurant.getName(), restaurant.getMenu())))
                .collect(Collectors.toList());
    }

    public void registerMeal(String restaurant, String name) {
        Restaurant restaurantAgg = this.restaurantRepositoryPort.getById(restaurant);
        restaurantAgg.registerMeal(name);
        this.restaurantRepositoryPort.persist(restaurantAgg);
    }

    @Override
    public List<String> retrievePreparementOverview(String restaurant) {
        return this.mealPreparementRepositoryPort.getByRestaurant(restaurant).getOverview();
    }

    @Override
    public void mealPrepared(String restaurant, String name) {

    }
}
