package com.vmw.sample.restaurantservice.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantApplicationService implements RestaurantApplicationPort {

    private final RestaurantRepositoryPort restaurantRepositoryPort;

    @Override
    public List<Menu> retrieveMenu() {
        final List<Restaurant> restaurants = this.restaurantRepositoryPort.getAll();
        return restaurants.stream()
                .map((restaurant -> new Menu(restaurant.getName(), restaurant.getMenu())))
                .collect(Collectors.toList());
    }

    public CompletableFuture<String> registerMeal(String name) {
        return CompletableFuture.completedFuture(name);
    }
}
