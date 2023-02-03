package com.vmw.sample.restaurantservice.core;

import java.util.List;

public interface MealPreparementRepositoryPort {

    List<MealPreparement> getAll();

    MealPreparement getByRestaurant(String restaurant);

    void persist(MealPreparement restaurant);
}
