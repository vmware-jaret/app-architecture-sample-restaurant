package com.vmw.sample.restaurantservice.secondary;

import com.vmw.sample.restaurantservice.core.MenuRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class JpaMenuRepositoryAdapter implements MenuRepositoryPort {

    private final MenuRepository menuRepository;

    @Override
    public String persistMeal(String name) {
        final MealEntity entity = MealEntity.builder().name(name).build();
        final MealEntity result = this.menuRepository.save(entity);
        return result.getId().toString();
    }
}
