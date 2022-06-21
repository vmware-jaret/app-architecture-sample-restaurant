package com.vmw.sample.restaurantservice.secondary;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="restaurants")
public class RestaurantEntity {

    @Getter
    @Setter
    @EmbeddedId
    private RestaurantId id;

    @Getter
    @Setter
    @Version
    @Column
    private Long version;

    @Getter
    @Setter
    @OneToMany(mappedBy="restaurant", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MealEntity> meals;
}
