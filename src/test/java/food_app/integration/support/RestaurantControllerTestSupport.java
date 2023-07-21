package food_app.integration.support;

import Food_app.api.dto.*;
import Food_app.domain.FoodApiMeal;
import Food_app.domain.FoodApiMealDetails;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface RestaurantControllerTestSupport {

    RequestSpecification requestSpecification();

    default List<RestaurantOrderDTO> restaurantActiveOrders(String restaurantName) {
        return List.of(requestSpecification()
                .get("/api/restaurant/{restaurantName}/activeOrders", restaurantName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(RestaurantOrderDTO[].class));
    }

    default void restaurantCompleteOrder(String restaurantName) {
        requestSpecification()
                .patch("/api/restaurant/completeOrder/{orderNumber}", restaurantName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    default void restaurantUpdateMeal(String restaurantName, String mealName, UpdateMealDTO updateMealDTO) {
      requestSpecification()
              .body(updateMealDTO)
                .put("/api/restaurant/{restaurantName}/updateMeal/{mealName}", restaurantName,mealName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }


}
