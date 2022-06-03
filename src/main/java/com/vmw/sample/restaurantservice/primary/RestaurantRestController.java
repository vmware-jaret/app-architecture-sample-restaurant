package com.vmw.sample.restaurantservice.primary;

import com.vmw.sample.restaurantservice.core.RestaurantApplicationPort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant/v1")
@AllArgsConstructor
public class RestaurantRestController {
    private final RestaurantApplicationPort restaurantApplicationPort;

    @PostMapping("/register-meal")
    public RegisterMealResponse registerMeal(@RequestBody RegisterMealRequest registerMealRequest) throws Exception {
        final String id = this.restaurantApplicationPort.registerMeal(registerMealRequest.getName()).get();
        return new RegisterMealResponse(id);
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
}
