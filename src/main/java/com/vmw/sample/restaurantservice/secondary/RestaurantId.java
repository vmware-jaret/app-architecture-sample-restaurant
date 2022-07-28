package com.vmw.sample.restaurantservice.secondary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RestaurantId implements Serializable {

    @Column(name = "name")
    @Getter
    @Setter
    public String name;
}
