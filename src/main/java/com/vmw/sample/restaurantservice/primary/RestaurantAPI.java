package com.vmw.sample.restaurantservice.primary;

import com.vmw.sample.restaurantservice.core.RestaurantMenu;
import com.vmw.sample.restaurantservice.core.RestaurantApplicationPort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant/v1")
@AllArgsConstructor
public class RestaurantAPI {
    private final RestaurantApplicationPort restaurantApplicationPort;

    @PostMapping("/register-meal")
    public void registerMeal(@RequestBody RegisterMealRequest body) {
        this.restaurantApplicationPort.registerMeal(body.getRestaurant(), body.getMeal());
    }

    @GetMapping("/retrieve-menu")
    public RetrieveMenuResponse retrieveMenu() {
        final List<RestaurantMenu> menus = this.restaurantApplicationPort.retrieveMenu();
        return new RetrieveMenuResponse(menus);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterMealRequest {
        private String restaurant;
        private String meal;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrieveMenuResponse {
        private List<RestaurantMenu> menus;
    }
}
