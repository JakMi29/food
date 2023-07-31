package food_app.integration.rest;

import Food_app.api.dto.CustomerOrderDTO;
import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.domain.FoodApiMeal;
import Food_app.domain.FoodApiMealDetails;
import food_app.integration.configuration.RestAssuredIntegrationTestBase;
import food_app.integration.support.CustomerControllerTestSupport;
import food_app.integration.support.WiremockTestSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class CustomerRestControllerIT
        extends RestAssuredIntegrationTestBase
        implements CustomerControllerTestSupport, WiremockTestSupport {

    @Test
    void restaurantTest() {
        List<RestaurantDTO> restaurantsFirstPage = restaurant(0);
        List<RestaurantDTO> restaurantsSecondPage = restaurant(1);

        Assertions.assertEquals(6, restaurantsFirstPage.size());
        Assertions.assertEquals(1, restaurantsSecondPage.size());
    }

    @Test
    void restaurantMenuTest() {
        String restaurant1 = "Flavors of est";
        String restaurant2 = "Da Grasso";
        String restaurant3 = "Sushi Master";
        String restaurant4 = "Polish Pot";
        String restaurant5 = "La Italiano";
        String restaurant6 = "Asian Food";
        String restaurant7 = "World Cuisine";
        RestaurantMenuDTO restaurantMenuDTO1 = restaurantMenu(restaurant1);
        RestaurantMenuDTO restaurantMenuDTO2 = restaurantMenu(restaurant2);
        RestaurantMenuDTO restaurantMenuDTO3 = restaurantMenu(restaurant3);
        RestaurantMenuDTO restaurantMenuDTO4 = restaurantMenu(restaurant4);
        RestaurantMenuDTO restaurantMenuDTO5 = restaurantMenu(restaurant5);
        RestaurantMenuDTO restaurantMenuDTO6 = restaurantMenu(restaurant6);
        RestaurantMenuDTO restaurantMenuDTO7 = restaurantMenu(restaurant7);

        Assertions.assertEquals(restaurant1, restaurantMenuDTO1.getName());
        Assertions.assertEquals(restaurant2, restaurantMenuDTO2.getName());
        Assertions.assertEquals(restaurant3, restaurantMenuDTO3.getName());
        Assertions.assertEquals(restaurant4, restaurantMenuDTO4.getName());
        Assertions.assertEquals(restaurant5, restaurantMenuDTO5.getName());
        Assertions.assertEquals(restaurant6, restaurantMenuDTO6.getName());
        Assertions.assertEquals(restaurant7, restaurantMenuDTO7.getName());

        Assertions.assertEquals(6, restaurantMenuDTO1.getMeals().size());
        Assertions.assertEquals(5, restaurantMenuDTO2.getMeals().size());
        Assertions.assertEquals(5, restaurantMenuDTO3.getMeals().size());
        Assertions.assertEquals(5, restaurantMenuDTO4.getMeals().size());
        Assertions.assertEquals(5, restaurantMenuDTO5.getMeals().size());
        Assertions.assertEquals(5, restaurantMenuDTO6.getMeals().size());
        Assertions.assertEquals(5, restaurantMenuDTO7.getMeals().size());
    }

    @Test
    void customerActiveOrdersTest() {
        String existingCustomer = "customerJohn";
        List<CustomerOrderDTO> activeOrders = customerActiveOrders(existingCustomer);
        Assertions.assertTrue(activeOrders.size()>0);
    }

    @Test
    void createOrder() {
        List<CustomerOrderDTO> ordersBefore=customerActiveOrders("customerJohn");
        customerCreateOrder(Map.of("Lemonade", "1"), "customerJohn", "La Italiano");
        List<CustomerOrderDTO> ordersAfter=customerActiveOrders("customerJohn");
        assertEquals(ordersBefore.size()+1,ordersAfter.size());
    }

    @Test
    void cancelOrder() {
        List<CustomerOrderDTO> ordersBeforeAddOrderToCancel=customerActiveOrders("customerJohn");
        customerCreateOrder(Map.of("Lemonade", "1"), "customerJohn", "La Italiano");
        List<CustomerOrderDTO> ordersAfterAddOrderToCancel=customerActiveOrders("customerJohn");
        String orderNumber=ordersAfterAddOrderToCancel.stream()
                .filter(c->!ordersBeforeAddOrderToCancel.contains(c))
                .map(CustomerOrderDTO::getOrderNumber)
                .reduce((x,y)->y+x).orElseThrow();
        customerCancelOrder(orderNumber);
        List<CustomerOrderDTO> ordersAfterCancelOrders=customerActiveOrders("customerJohn");
        assertEquals(ordersBeforeAddOrderToCancel.size(),ordersAfterCancelOrders.size());
        assertFalse(ordersAfterCancelOrders.stream().map(CustomerOrderDTO::getOrderNumber).toList().contains(orderNumber));

    }

    @Test
    void customerMexicanMonthTest() {
        stubForMexicanMonthMeals(wireMockServer);
        List<FoodApiMeal> foodApiMeals = customerMexicanMonth(1);
        assertEquals(5, foodApiMeals.size());
    }

    @Test
    void customerMexicanMonthReceiptTest() {
        stubForMexicanMonthRecipe(wireMockServer);
        FoodApiMealDetails foodApiMealDetails = customerMexicanMonthRecipe(1);
        assertEquals("Pressure cooker refried beans", foodApiMealDetails.getTitle());
        assertEquals(1, foodApiMealDetails.getId());
        assertEquals("https://apipics.s3.amazonaws.com/mexican_api/1.jpg", foodApiMealDetails.getImage());
    }


}
