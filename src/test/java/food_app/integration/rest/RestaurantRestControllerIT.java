package food_app.integration.rest;

import Food_app.api.dto.MealDTO;
import Food_app.api.dto.RestaurantOrderDTO;
import Food_app.api.dto.UpdateMealDTO;
import food_app.integration.configuration.RestAssuredIntegrationTestBase;
import food_app.integration.support.CustomerControllerTestSupport;
import food_app.integration.support.RestaurantControllerTestSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RestaurantRestControllerIT
        extends RestAssuredIntegrationTestBase
        implements RestaurantControllerTestSupport,CustomerControllerTestSupport {

    @Test
    void restaurantActiveOrdersTest() {
        String existingRestaurant = "Sushi Master";
        List<RestaurantOrderDTO> activeOrders = restaurantActiveOrders(existingRestaurant);
        Assertions.assertEquals(4, activeOrders.size());
    }

    @Test
    void restaurantCompleteOrdersTest() {
        String existingRestaurant = "Sushi Master";
        List<RestaurantOrderDTO> activeOrdersBefore = restaurantActiveOrders(existingRestaurant);
        String orderToComplete=activeOrdersBefore.get(0).getOrderNumber();
        restaurantCompleteOrder(orderToComplete);
        List<RestaurantOrderDTO> activeOrdersAfter = restaurantActiveOrders(existingRestaurant);
        Assertions.assertEquals(activeOrdersBefore.size()-1, activeOrdersAfter.size());
        assertFalse(activeOrdersAfter.stream().map(RestaurantOrderDTO::getOrderNumber).toList().contains(orderToComplete));
    }
    @Test
    void restaurantUpdateMeal(){
        String newMealName="newMealName";
        String newMealDescription="newMealDescription";
        String newMMealCategory="newMMealCategory";
        String newMealPrice="14.00";
        UpdateMealDTO updateMealDTO=UpdateMealDTO.builder()
                .name(newMealName)
                .category(newMMealCategory)
                .price(newMealPrice)
                .description(newMealDescription)
                .build();

        restaurantUpdateMeal("Sushi Master","Lemonade",updateMealDTO);

        List<MealDTO> MealDTOList=restaurantMenu("Sushi Master").getMeals();

        assertTrue(MealDTOList.stream().map(MealDTO::getName).toList().contains(newMealName));
        assertTrue(MealDTOList.stream().map(MealDTO::getCategory).toList().contains(newMMealCategory));
        assertTrue(MealDTOList.stream().map(MealDTO::getDescription).toList().contains(newMealDescription));
        assertTrue(MealDTOList.stream().map(MealDTO::getPrice).toList().contains(newMealPrice));

    }


}
