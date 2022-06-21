package com.vmw.sample.restaurantservice.primary;

import com.vmw.sample.restaurantservice.core.Menu;
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
    public RegisterMealResponse registerMeal(@RequestBody RegisterMealRequest registerMealRequest) throws Exception {
        final String id = this.restaurantApplicationPort.registerMeal(registerMealRequest.getName()).get();
        return new RegisterMealResponse(id);
    }

    @GetMapping("/retrieve-menu")
    public RetrieveMenuResponse retrieveMenu() {
        final List<Menu> menus = this.restaurantApplicationPort.retrieveMenu();
        return new RetrieveMenuResponse(menus);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterMealRequest {
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterMealResponse {
        private String id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrieveMenuResponse {
        private List<Menu> menus;
    }
}
