package Food_app.api.controller.rest;

import Food_app.api.dto.CustomerOrderDTO;
import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.api.dto.mapper.CustomerOrderMapper;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.business.*;
import Food_app.domain.Customer;
import Food_app.domain.FoodApiMeal;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(CustomerRestController.API_CUSTOMER)
public class CustomerRestController {

    public static final String API_CUSTOMER = "/api/customer";
    public static final String RESTAURANTS = "/restaurants/{pageNumber}";
    public static final String CREATE_ORDER = "/{name}/order/{restaurantName}";
    public static final String RESTAURANT_MENU = "/restaurantMenu/{restaurantName}";
    public static final String CANCEL_ORDER = "/cancelOrder/{orderNumber}";
    public static final String ACTIVE_ORDERS = "/{name}/activeOrders";
    public static final String MEXICAN_MONTH = "/mexicanMonth/meals/{pageNumber}";
    public static final String MEXICAN_MONTH_RECEIPT = "/mexicanMonth/mealDetails/{mealId}";

    private final RestaurantPaginationService restaurantPaginationService;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final RestaurantMapper restaurantMapper;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final FoodApiService foodApiService;
    private final FoodApiDetailsMapper foodApiDetailsMapper;
    private final RestaurantMenuMapper restaurantMenuMapper;
    private final CustomerOrderMapper customerOrderMapper;


    @GetMapping(value = RESTAURANTS)
    public ResponseEntity<List<RestaurantDTO>> restaurants(
            @PathVariable Integer pageNumber
    ) {

        Page<RestaurantEntity> page = restaurantPaginationService.paginate(pageNumber, 5);
        List<RestaurantDTO> restaurants = page.stream()
                .map(restaurantEntityMapper::map)
                .map(restaurantMapper::map)
                .toList();

        return ResponseEntity
                .ok(restaurants);

    }

    @PostMapping(value = CREATE_ORDER)
    public void createOrder(
            @PathVariable String name,
            @PathVariable String restaurantName,
            @RequestBody Map<String, String> mealMap
    ) {
        orderService.createOrder(name, restaurantName, mealMap);
    }

    @GetMapping(value = ACTIVE_ORDERS)
    public ResponseEntity<List<CustomerOrderDTO>> activeOrders(@PathVariable String name) {
        Customer customer = customerService.findCustomerByUserNameWithOrders(name);
        List<CustomerOrderDTO> orders = customer.getOrders().stream()
                .filter(c -> !c.getComplete())
                .map(customerOrderMapper::map)
                .toList();

        return ResponseEntity
                .ok(orders);
    }

    @GetMapping(value = MEXICAN_MONTH)
    public ResponseEntity<List<FoodApiMeal>>mexicanMonth( @PathVariable Integer pageNumber) {
        List<FoodApiMeal> meals = foodApiService.getFoodMealPage(pageNumber);
        return ResponseEntity
                .ok(meals);
    }

    @GetMapping(value = MEXICAN_MONTH_RECEIPT)
    public ResponseEntity<FoodApiMealDetailsDTO> mexicanMonthReceipt( @PathVariable Integer mealId) {
        FoodApiMealDetailsDTO meal = foodApiDetailsMapper.map(foodApiService.getFoodApiMealDetails(mealId));
        return ResponseEntity
                .ok(meal);
    }

    @GetMapping(value = RESTAURANT_MENU)
    public ResponseEntity<RestaurantMenuDTO> restaurantMenu(@PathVariable String restaurantName) {
        RestaurantMenuDTO restaurantDTO = restaurantMenuMapper.map(restaurantService.findRestaurantByNameWithMeals(restaurantName));
        return ResponseEntity
                .ok(restaurantDTO);
    }

    @DeleteMapping(value = CANCEL_ORDER)
    public ResponseEntity cancelOrder(@PathVariable String orderNumber) {
        orderService.cancelOrder(orderNumber);
        return ResponseEntity.ok(orderNumber);
    }
}
