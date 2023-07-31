package food_app.integration.support;

import Food_app.api.dto.RestaurantOrderDTO;
import Food_app.api.dto.UpdateMealDTO;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface RestaurantControllerTestSupport {

    RequestSpecification requestSpecification();

    default List<RestaurantOrderDTO> restaurantActiveOrders(String restaurantName) {
        return List.of(requestSpecification()
                .get("/api/restaurant/{restaurantName}/orders", restaurantName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(RestaurantOrderDTO[].class));
    }

    default void restaurantCompleteOrder(String restaurantName) {
        requestSpecification()
                .patch("/api/restaurant/order/{orderNumber}", restaurantName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }

    default void restaurantUpdateMeal(String restaurantName, String mealName, UpdateMealDTO updateMealDTO) {
      requestSpecification()
              .body(updateMealDTO)
                .put("/api/restaurant/{restaurantName}/meal/{mealName}", restaurantName,mealName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }


}
