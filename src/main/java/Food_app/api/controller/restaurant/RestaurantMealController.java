package Food_app.api.controller.restaurant;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.business.MealService;
import Food_app.business.RestaurantService;
import Food_app.domain.Restaurant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RestaurantMealController {
    public static final String RESTAURANT_DELETE_MEAL = "/restaurant/{name}/deleteMeal/{mealName}";
    public static final String RESTAURANT_DELETE_MEAL_PAGE = "create_meal";
    public static final String RESTAURANT_CREATE_MEAL = "/restaurant/{name}/addMeal";
    public static final String RESTAURANT_UPDATE_MEAL = "/restaurant/{name}/updateMeal/{mealName}";
    public static final String RESTAURANT_UPDATE_MEAL_PAGE = "update_meal";
    public static final String RESTAURANT_REDIRECT_MENU_PAGE = "redirect:/restaurant/%s/menu";
    public static final String RESTAURANT_REDIRECT_MENU = "redirect:/restaurant/%s/menu";
    public static final String RESTAURANT_MEAL_DONE_PAGE = "meal_done";

    private final MealService mealService;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

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
