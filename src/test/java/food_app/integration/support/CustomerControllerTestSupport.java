package food_app.integration.support;

import Food_app.api.dto.CustomerOrderDTO;
import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.domain.FoodApiMeal;
import Food_app.domain.FoodApiMealDetails;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public interface CustomerControllerTestSupport {

    RequestSpecification requestSpecification();

    default List<RestaurantDTO> restaurant(int pageNumber) {
        return List.of(requestSpecification()
                .get("/api/customer/restaurants/{pageNumber}/6", pageNumber)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(RestaurantDTO[].class));
    }

    default RestaurantMenuDTO restaurantMenu(String restaurantName) {
        return requestSpecification()
                .get("/api/customer/restaurantMenu/{restaurantName}", restaurantName)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(RestaurantMenuDTO.class);
    }

    default String customerActiveOrdersFail(String ame) {
        return requestSpecification()
                .get("/api/customer/{name}/orders", ame)
                .then()
                .extract()
                .contentType();
    }

    default List<CustomerOrderDTO> customerActiveOrders(String name) {
        return List.of(requestSpecification()
                .get("/api/customer/{name}/orders", name)
                .then()
                .extract()
                .as(CustomerOrderDTO[].class));

    }

    default void customerCancelOrder(String orderNumber) {
        requestSpecification()
                .delete("/api/customer/order/{orderNumber}", orderNumber)
                .then()
                .extract();
    }
    default void customerCreateOrder(final Map<String, String> mealMap, String name, String restaurantName) {
        requestSpecification()
                .body(mealMap)
                .post("/api/customer/{name}/order/{restaurantName}", name, restaurantName)
                .then()
                .extract();

    }

    default List<FoodApiMeal> customerMexicanMonth(int pageNumber) {
        return List.of(requestSpecification()
                .get("/api/mexicanMonth/meals/{pageNumber}", pageNumber)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(FoodApiMeal[].class));
    }

    default FoodApiMealDetails customerMexicanMonthRecipe(int mealId) {
        return requestSpecification()
                .get("/api/mexicanMonth/mealDetails/{mealId}", mealId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(FoodApiMealDetails.class);
    }

}
