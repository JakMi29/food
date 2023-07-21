package Food_app.api.controller;

import Food_app.api.dto.*;
import Food_app.api.dto.mapper.CustomerMapper;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.business.*;
import Food_app.domain.Customer;
import Food_app.domain.FoodApiMeal;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.security.UserEntity;
import Food_app.infrastructure.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_HOME = "/customer";
    public static final String CUSTOMER_MEXICAN_MONTH = "/customer/{name}/mexicanMonth/{pageNumber}";
    public static final String CUSTOMER_MEXICAN_MONTH_RECEIPT = "/customer/{name}/mexicanMonth/receipt/{id}";
    public static final String CUSTOMER_RESTAURANTS = "/customer/{name}/restaurants/{pageNumber}";
    public static final String CUSTOMER_RESTAURANTS_PAGE = "customer/customer_restaurants";
    public static final String CUSTOMER_RESTAURANTS_BY_STREET = "/customer/{name}/restaurantsByStreet";
    public static final String CUSTOMER_ORDER_HISTORY = "/customer/{name}/orders";
    public static final String CUSTOMER_RESTAURANT_MENU = "customer/{name}/restaurantMenu/{restaurantName}";
    public static final String CREATE_ORDER = "/customer/{name}/order/{restaurantName}";
    public static final String CANCEL_ORDER = "/customer/{name}/cancelOrder/{orderNumber}";
    public static final String CUSTOMER_ORDER_DETAILS = "/customer/{name}/orderDetails/{orderNumber}";
    public static final String CUSTOMER_REDIRECT_RESTAURANTS_PAGE = "redirect:/customer/%s/restaurants/0";
    public static final String CUSTOMER_ORDERS_PAGE = "redirect:/customer/%s/orders";
    public static final String CUSTOMER_ORDER_DETAILS_PAGE = "customer/customer_order_details";
    public static final String CUSTOMER_RESTAURANTS_BY_STREET_PAGE = "customer/customer_restaurants_by_street";
    public static final String CUSTOMER_RESTAURANT_MENU_PAGE = "customer/customer_restaurant_menu";
    public static final String CUSTOMER_ORDER_HISTORY_PAGE = "customer/customer_order_history";
    public static final String CUSTOMER_HOME_PAGE = "customer/customer_home_page";
    public static final String CUSTOMER_MEXICAN_MONTH_PAGE = "customer/customer_mexican_month";
    public static final String CUSTOMER_MEXICAN_MONTH_RECEIPT_PAGE = "customer/customer_mexican_month_receipt";
    private final UserRepository userRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;
    private final RestaurantMenuMapper restaurantMenuMapper;

    private final OrderService orderService;

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final RestaurantPaginationService restaurantPaginationService;
    private final FoodApiService foodApiService;
    private final FoodApiDetailsMapper foodApiDetailsMapper;


    @GetMapping(value = CUSTOMER_HOME)
    public String homePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByUserName(userDetails.getUsername());

        Customer customer = customerService.findCustomerByUserName(user.getUserName());
        model.addAttribute("name", customer.getUserName());
        return CUSTOMER_HOME_PAGE;
    }

    @GetMapping(value = CUSTOMER_MEXICAN_MONTH)
    public String mexicanMonth(@PathVariable String name, @PathVariable Integer pageNumber, Model model) {
        List<FoodApiMeal> meals = foodApiService.getFoodMealPage(pageNumber);
        model.addAttribute("name", name);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("foodApiMeals", meals);
        model.addAttribute("isLastPage", meals.size() != 5);
        return CUSTOMER_MEXICAN_MONTH_PAGE;
    }

    @GetMapping(value = CUSTOMER_MEXICAN_MONTH_RECEIPT)
    public String mexicanMonthReceipt(@PathVariable String name, @PathVariable Integer id, Model model) {
        FoodApiMealDetailsDTO meal = foodApiDetailsMapper.map(foodApiService.getFoodApiMealDetails(id));
        model.addAttribute("name", name);
        model.addAttribute("foodApiMeal", meal);
        return CUSTOMER_MEXICAN_MONTH_RECEIPT_PAGE;
    }

    @GetMapping(value = CUSTOMER_RESTAURANTS)
    public String restaurants(@PathVariable String name, @PathVariable Integer pageNumber, Model model) {
        Page<RestaurantEntity> page = restaurantPaginationService.paginate(pageNumber, 5);
        List<RestaurantDTO> restaurants = page.stream().map(restaurantEntityMapper::map).map(restaurantMapper::map).toList();
        model.addAttribute("name", name);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("isLastPage", page.isLast());
        model.addAttribute("restaurants", restaurants);
        return CUSTOMER_RESTAURANTS_PAGE;
    }

    @GetMapping(value = CUSTOMER_RESTAURANTS_BY_STREET)
    public String restaurantsByStreet(@PathVariable String name, @RequestParam("streetName") String streetName, Model model) {
        List<RestaurantDTO> restaurants = restaurantService.findRestaurantsByDeliveryStreet(streetName).stream().map(restaurantMapper::map).toList();
        model.addAttribute("name", name);
        model.addAttribute("restaurants", restaurants);
        return CUSTOMER_RESTAURANTS_BY_STREET_PAGE;
    }

    @GetMapping(value = CUSTOMER_ORDER_HISTORY)
    public String ordersPage(@PathVariable String name, Model model) {
        CustomerDTO customer = customerMapper.mapWithOrders(customerService.findCustomerByUserNameWithOrders(name));
        Map<Boolean, List<CustomerOrderDTO>> orders = sortOrders(customer.getOrders());
        model.addAttribute("name", name);
        model.addAttribute("completeOrders", orders.get(true));
        model.addAttribute("activeOrders", orders.get(false));
        return CUSTOMER_ORDER_HISTORY_PAGE;
    }

    private Map<Boolean, List<CustomerOrderDTO>> sortOrders(List<CustomerOrderDTO> orders) {
        return orders.stream().collect(Collectors.partitioningBy(CustomerOrderDTO::getComplete));
    }

    @GetMapping(value = CUSTOMER_RESTAURANT_MENU)
    public String restaurantMenu(@PathVariable String name, @PathVariable String restaurantName, Model model) {
        RestaurantMenuDTO restaurantDTO = restaurantMenuMapper.map(restaurantService.findRestaurantByNameWithMeals(restaurantName));
        model.addAttribute("name", name);
        model.addAttribute("restaurantName", restaurantName);
        model.addAttribute("restaurant", restaurantDTO);
        return CUSTOMER_RESTAURANT_MENU_PAGE;
    }


    @GetMapping(value = CUSTOMER_ORDER_DETAILS)
    public String orderDetails(@PathVariable String name, @PathVariable String orderNumber, Model model) {
        OrderDetailsDTO order = orderService.getOrderDetails(orderNumber);
        model.addAttribute("order", order);
        model.addAttribute("name", name);
        return CUSTOMER_ORDER_DETAILS_PAGE;
    }

    @DeleteMapping(value = CANCEL_ORDER)
    public String cancelOrder(@PathVariable String name, @PathVariable String orderNumber, Model model) {
        orderService.cancelOrder(orderNumber);
        return CUSTOMER_ORDERS_PAGE.formatted(name);
    }

    @PostMapping(value = CREATE_ORDER)
    public String createOrder(
            @PathVariable String name,
            @PathVariable String restaurantName,
            @RequestParam Map<String, String> mealMap
    ) {
        orderService.createOrder(name, restaurantName, mapMap(mealMap));
        return CUSTOMER_REDIRECT_RESTAURANTS_PAGE.formatted(name);
    }


    private Map<String, String> mapMap(Map<String, String> map) {
        Map<String, String> newMap = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();

            if (paramName.startsWith("mealQuantities")) {
                String mealName = paramName.substring(paramName.indexOf("[") + 1, paramName.indexOf("]"));
                newMap.put(mealName, paramValue);
            }
        }
        return newMap;
    }


}
