package com.vmw.sample.restaurantservice.core;

import java.util.List;

public interface RestaurantRepositoryPort {

    List<Restaurant> getAll();

    Restaurant getById(String name);

    void persist(Restaurant restaurant);
}
