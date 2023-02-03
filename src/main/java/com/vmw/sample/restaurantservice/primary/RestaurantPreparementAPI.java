package com.vmw.sample.restaurantservice.primary;

import com.vmw.sample.restaurantservice.core.RestaurantApplicationPort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant/v1")
@AllArgsConstructor
public class RestaurantPreparementAPI {
    private final RestaurantApplicationPort restaurantApplicationPort;

    @PostMapping("/meal-prepared")
    public void registerMealAsPreparedRequest(@RequestBody RegisterMealAsPreparedRequest body) {
        this.restaurantApplicationPort.mealPrepared(body.getRestaurant(), body.getMeal());
    }

    @GetMapping("/preparement/{restaurantId}")
    public RetrievePreparementOverviewResponse retrievePreparementOverview(@PathVariable String restaurantId) {
        final List<String> meals = this.restaurantApplicationPort.retrievePreparementOverview(restaurantId);
        return new RetrievePreparementOverviewResponse(meals);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterMealAsPreparedRequest {
        private String restaurant;
        private String meal;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrievePreparementOverviewResponse {
        private List<String> meals;
    }
}
