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
        RestaurantId id = new RestaurantId();
        id.setName(restaurant.getName());

        entity.setId(id);
        entity.setMeals(restaurant.getMenu().stream().map((meal) -> this.convertMeal(id, meal)).collect(Collectors.toSet()));
        return entity;
    }

    private MealEntity convertMeal(RestaurantId restaurantId, String meal) {
        MealEntity mealEntity = new MealEntity();
        MealId mealId = new MealId();
        mealId.setRestaurantId(restaurantId);
        mealId.setName(meal);
        mealEntity.setId(mealId);
        return mealEntity;
    }
}
