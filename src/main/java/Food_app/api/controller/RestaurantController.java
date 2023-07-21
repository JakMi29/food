package Food_app.api.controller;

import Food_app.api.dto.*;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.business.*;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantOwner;
import Food_app.domain.RestaurantStreet;
import Food_app.domain.Street;
import Food_app.infrastructure.database.repository.mapper.UserEntityMapper;
import Food_app.infrastructure.security.UserEntity;
import Food_app.infrastructure.security.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class RestaurantController {

    public static final String RESTAURANT = "/restaurant";
    public static final String RESTAURANT_PAGE = "restaurant/restaurantHomePage";
    public static final String RESTAURANT_MENU = "/restaurant/{name}/menu";
    public static final String RESTAURANT_REDIRECT_MENU = "redirect:/restaurant/%s/menu";
    public static final String RESTAURANT_MENU_PAGE = "restaurant/restaurant_menu";
    public static final String RESTAURANT_ORDER_DETAILS = "/restaurant/{name}/orderDetails/{orderNumber}";
    public static final String RESTAURANT_ORDER_DETAILS_PAGE = "restaurant/restaurant_order_details";
    public static final String RESTAURANT_DELIVERY = "/restaurant/{name}/delivery";
    public static final String RESTAURANT_DELIVERY_PAGE = "restaurant/restaurant_delivery";
    public static final String RESTAURANT_ADD_STREET = "/restaurant/{name}/addStreet";
    public static final String RESTAURANT_ADD_STREET_PAGE = "create_restaurant_street";

    public static final String RESTAURANT_REMOVE_STREET = "/restaurant/{name}/removeStreet/{streetName}";
    public static final String RESTAURANT_REDIRECT_RESTAURANT_DELIVERY = "redirect:/restaurant/%s/delivery";

    public static final String RESTAURANT_ORDERS = "/restaurant/{name}/orders";
    public static final String RESTAURANT_REDIRECT_ORDERS = "redirect:/restaurant/%s/orders";
    public static final String RESTAURANT_ORDERS_PAGE = "restaurant/restaurantOrderHistory";
    public static final String RESTAURANT_COMPLETE_ORDER = "/restaurant/{name}/completeOrder/{orderNumber}";

    public static final String RESTAURANT_DELETE_MEAL = "/restaurant/{name}/deleteMeal/{mealName}";
    public static final String RESTAURANT_DELETE_MEAL_PAGE = "create_meal";


    public static final String RESTAURANT_CREATE_MEAL = "/restaurant/{name}/addMeal";
    public static final String RESTAURANT_UPDATE_MEAL = "/restaurant/{name}/updateMeal/{mealName}";
    public static final String RESTAURANT_UPDATE_MEAL_PAGE = "update_meal";
    public static final String RESTAURANT_REDIRECT_MENU_PAGE = "redirect:/restaurant/%s/menu";
    public static final String RESTAURANT_MEAL_DONE_PAGE = "meal_done";

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RestaurantOwnerService restaurantOwnerService;
    private final RestaurantService restaurantService;
    private final MealService mealService;
    private final RestaurantMapper restaurantMapper;
    private final OrderService orderService;
    private final RestaurantStreetService restaurantStreetService;


    @GetMapping(value = RESTAURANT)
    public String homePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByUserName(userDetails.getUsername());

        RestaurantOwner restaurantOwner = restaurantOwnerService.findByUser(userEntityMapper.map(user));
        Restaurant restaurant = restaurantService.findRestaurantByRestaurantOwnerEmail(restaurantOwner.getEmail());

        model.addAttribute("name", restaurant.getName());
        return RESTAURANT_PAGE;
    }

    @GetMapping(value = RESTAURANT_MENU)
    public String restaurantMenu(@PathVariable String name, Model model) {
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurantService.findRestaurantByNameWithMeals(name));
        model.addAttribute("meals", restaurantDTO.getMeals());
        model.addAttribute("name", restaurantDTO.getName());
        return RESTAURANT_MENU_PAGE;
    }

    @GetMapping(value = RESTAURANT_ORDER_DETAILS)
    public String orderDetails(@PathVariable String name, @PathVariable String orderNumber, Model model) {
        OrderDetailsDTO order = orderService.getOrderDetails(orderNumber);
        model.addAttribute("order", order);
        model.addAttribute("name", name);
        return RESTAURANT_ORDER_DETAILS_PAGE;
    }

    @GetMapping(value = RESTAURANT_DELIVERY)
    public String delivery(@PathVariable String name, Model model) {
        Restaurant restaurant = restaurantService.findRestaurantByNameWithOrders(name);
        List<Street> streets = mapStreet(restaurantStreetService.findRestaurantStreetsByRestaurant(restaurant));
        model.addAttribute("name", name);
        model.addAttribute("streets", streets);
        return RESTAURANT_DELIVERY_PAGE;
    }

    @GetMapping(value = RESTAURANT_ADD_STREET)
    public String addStreetForm(@PathVariable String name, Model model) {
        StreetDTO street = new StreetDTO();
        model.addAttribute("name", name);
        model.addAttribute("streetDTO", street);
        return RESTAURANT_ADD_STREET_PAGE;
    }

    @PostMapping(value = RESTAURANT_ADD_STREET)
    public String addStreet(
            @PathVariable String name,
            @ModelAttribute("streetDTO") StreetDTO streetDTO,
            Model model) {
        Restaurant restaurant = restaurantService.findRestaurantByNameWithMeals(name);
        restaurantStreetService.createRestaurantStreet(streetDTO.getName(), restaurant);
        model.addAttribute("name", name);
        return RESTAURANT_REDIRECT_RESTAURANT_DELIVERY.formatted(name);
    }

    private List<Street> mapStreet(List<RestaurantStreet> streets) {
        return streets.stream().map(RestaurantStreet::getStreet).toList();
    }

    @DeleteMapping(value = RESTAURANT_REMOVE_STREET)
    public String removeStreet(@PathVariable String name, @PathVariable String streetName, Model model) {
        Restaurant restaurant = restaurantService.findRestaurantByNameWithMeals(name);
        restaurantStreetService.removeStreetFromRestaurant(streetName, restaurant);
        model.addAttribute("name", name);
        return RESTAURANT_REDIRECT_RESTAURANT_DELIVERY.formatted(name);
    }


    @GetMapping(value = RESTAURANT_ORDERS)
    public String ordersPage(@PathVariable String name, Model model) {
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurantService.findRestaurantByNameWithOrders(name));
        Map<Boolean, List<RestaurantOrderDTO>> orders = sortOrders(restaurantDTO.getOrders());
        model.addAttribute("completeOrders", orders.get(true));
        model.addAttribute("activeOrders", orders.get(false));
        return RESTAURANT_ORDERS_PAGE;
    }

    private Map<Boolean, List<RestaurantOrderDTO>> sortOrders(List<RestaurantOrderDTO> orders) {
        return orders.stream().collect(Collectors.partitioningBy(RestaurantOrderDTO::getComplete));
    }

    @PatchMapping(value = RESTAURANT_COMPLETE_ORDER)
    public String updateOrder(@PathVariable String name, @PathVariable String orderNumber, Model model) {
        orderService.completeOrder(orderNumber);
        return RESTAURANT_REDIRECT_ORDERS.formatted(name);
    }

    @DeleteMapping(value = RESTAURANT_DELETE_MEAL)
    public String deleteMeal(@PathVariable String name, @PathVariable String mealName, Model model) {
        mealService.deleteByNameAndRestaurantName(mealName, name);
        model.addAttribute("name", name);
        return RESTAURANT_REDIRECT_MENU.formatted(name);
    }

    @GetMapping(value = RESTAURANT_CREATE_MEAL)
    public String createMealForm(@PathVariable String name, Model model) {
        CreateMealDTO meal = new CreateMealDTO();
        model.addAttribute("mealDTO", meal);
        model.addAttribute("name", name);
        return RESTAURANT_DELETE_MEAL_PAGE;
    }

    @PostMapping(value = RESTAURANT_CREATE_MEAL)
    public String makeMeal(
            @PathVariable String name,
            @Valid @ModelAttribute("mealDTO") CreateMealDTO mealDTO,
            ModelMap model
    ) {

        mealService.addMeal(mealDTO, name);

        model.addAttribute("mealName", mealDTO.getName());
        model.addAttribute("name", name);

        return RESTAURANT_MEAL_DONE_PAGE;
    }

    @GetMapping(value = RESTAURANT_UPDATE_MEAL)
    public String getUpdateAMeal(@PathVariable String name, @PathVariable String mealName, Model model) {
        CreateMealDTO meal = new CreateMealDTO();
        model.addAttribute("mealDTO", meal);
        model.addAttribute("name", name);
        model.addAttribute("meaName", mealName);
        return RESTAURANT_UPDATE_MEAL_PAGE;
    }


    @PutMapping(value = RESTAURANT_UPDATE_MEAL)
    public String updateMeal(
            @PathVariable String name,
            @PathVariable String mealName,
            @Valid @ModelAttribute("mealDTO") CreateMealDTO mealDTO,
            ModelMap model
    ) {
        mealService.updateMeal(name, mealName, mealDTO);
        Restaurant restaurant = restaurantService.findRestaurantByNameWithMeals(name);
        model.addAttribute("name", name);
        model.addAttribute("meals", restaurant.getMeals());
        return RESTAURANT_REDIRECT_MENU_PAGE.formatted(name);
    }
}
