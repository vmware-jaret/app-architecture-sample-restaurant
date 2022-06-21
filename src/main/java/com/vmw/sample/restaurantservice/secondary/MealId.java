package com.vmw.sample.restaurantservice.secondary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MealId implements Serializable {

    @Getter
    @Setter
    private RestaurantId restaurantId;

    @Getter
    @Setter
    @Column(name = "name")
    public String name;
}
