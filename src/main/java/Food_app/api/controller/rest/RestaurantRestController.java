package Food_app.api.controller.rest;

import Food_app.api.dto.RestaurantOrderDTO;
import Food_app.api.dto.UpdateMealDTO;
import Food_app.api.dto.mapper.RestaurantOrderMapper;
import Food_app.business.MealService;
import Food_app.business.OrderService;
import Food_app.business.RestaurantService;
import Food_app.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(RestaurantRestController.API_CUSTOMER)
public class RestaurantRestController {

    public static final String API_CUSTOMER = "/api/restaurant";
    public static final String COMPLETE_ORDER = "/order/{orderNumber}";
    public static final String ACTIVE_ORDERS = "/{restaurantName}/orders";
    public static final String UPDATE_MEAL = "/{restaurantName}/meal/{mealName}";


    private final OrderService orderService;
    private final MealService mealService;
    private final RestaurantService restaurantService;
    private final RestaurantOrderMapper restaurantOrderMapper;

    @GetMapping(value = ACTIVE_ORDERS)
    public ResponseEntity<List<RestaurantOrderDTO>> restaurants(
            @PathVariable String restaurantName
    ) {
        Restaurant restaurant = restaurantService.findRestaurantByNameWithOrders(restaurantName);
        List<RestaurantOrderDTO> orders = restaurant.getOrders().stream()
                .filter(c -> !c.getComplete())
                .map(restaurantOrderMapper::map)
                .toList();

        return ResponseEntity
                .ok(orders);

    }

    @PatchMapping(value = COMPLETE_ORDER)
    public void restaurantMenu(@PathVariable String orderNumber) {
        orderService.completeOrder(orderNumber);
    }

    @PutMapping(value = UPDATE_MEAL)
    public void updateMeal(
            @RequestBody UpdateMealDTO mealDTO,
            @PathVariable String restaurantName,
            @PathVariable String mealName) {
        mealService.updateMealWithoutPhoto(restaurantName, mealName, mealDTO);
    }


}
