package com.vmw.sample.restaurantservice.secondary;

import com.vmw.sample.restaurantservice.core.Restaurant;
import com.vmw.sample.restaurantservice.core.RestaurantNotFoundException;
import com.vmw.sample.restaurantservice.core.RestaurantRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JpaRestaurantRepositoryAdapter implements RestaurantRepositoryPort {

    private final RestaurantRepository repository;

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll().stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public Restaurant getById(String name) {
        return repository.findById(name).map(this::convert).orElseThrow(RestaurantNotFoundException::new);
    }

    @Override
    public void persist(Restaurant restaurant) {
        repository.save(convert(restaurant));
    }

    private Restaurant convert(RestaurantEntity entity) {
        return new Restaurant(
                entity.getId().getName(),
                entity.getMeals().stream().map((meal) -> meal.getId().getName()).collect(Collectors.toList())
        );
    }

    private RestaurantEntity convert(Restaurant restaurant) {
        RestaurantEntity entity = new RestaurantEntity();
        // TODO: implement
        return entity;
    }
}
