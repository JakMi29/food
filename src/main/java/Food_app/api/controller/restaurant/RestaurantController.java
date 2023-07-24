package Food_app.api.controller.restaurant;

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

    public static final String RESTAURANT_MENU_PAGE = "restaurant/restaurant_menu";

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RestaurantOwnerService restaurantOwnerService;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;


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




}
