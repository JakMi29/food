package Food_app.api.controller.restaurant;

import Food_app.api.dto.StreetDTO;
import Food_app.business.RestaurantService;
import Food_app.business.RestaurantStreetService;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantStreet;
import Food_app.domain.Street;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestaurantDeliveryController {
    public static final String RESTAURANT_DELIVERY = "/restaurant/{name}/delivery";
    public static final String RESTAURANT_DELIVERY_PAGE = "restaurant/restaurant_delivery";
    public static final String RESTAURANT_ADD_STREET = "/restaurant/{name}/addStreet";
    public static final String RESTAURANT_ADD_STREET_PAGE = "create_restaurant_street";

    public static final String RESTAURANT_REMOVE_STREET = "/restaurant/{name}/removeStreet/{streetName}";
    public static final String RESTAURANT_REDIRECT_RESTAURANT_DELIVERY = "redirect:/restaurant/%s/delivery";

    private final RestaurantStreetService restaurantStreetService;
    private final RestaurantService restaurantService;


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
}
