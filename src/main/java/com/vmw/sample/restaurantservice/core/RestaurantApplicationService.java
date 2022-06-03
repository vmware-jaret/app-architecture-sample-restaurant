package com.vmw.sample.restaurantservice.core;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class RestaurantApplicationService implements RestaurantApplicationPort {

    private final MenuRepositoryPort menuRepositoryPort;

    public CompletableFuture<String> registerMeal(String name) {
        return CompletableFuture.completedFuture(menuRepositoryPort.persistMeal(name));
    }
}
