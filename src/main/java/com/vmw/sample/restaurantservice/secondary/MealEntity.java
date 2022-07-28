package com.vmw.sample.restaurantservice.secondary;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="meals")
public class MealEntity {

    @Getter
    @Setter
    @EmbeddedId
    private MealId id;

    @MapsId("restaurantId")
    @ManyToOne
    @JoinColumn(name="restaurant_name", referencedColumnName = "name")
    private RestaurantEntity restaurant;
}
