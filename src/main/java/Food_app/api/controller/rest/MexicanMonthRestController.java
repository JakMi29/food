package Food_app.api.controller.rest;

import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.business.FoodApiService;
import Food_app.domain.FoodApiMeal;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(MexicanMonthRestController.API_MEXICAN_MONTH)
public class MexicanMonthRestController {
    public static final String API_MEXICAN_MONTH = "/api/mexicanMonth";
    public static final String MEXICAN_MONTH = "/meals/{pageNumber}";
    public static final String MEXICAN_MONTH_RECEIPT = "/mealDetails/{mealId}";

    private final FoodApiService foodApiService;
    private final FoodApiDetailsMapper foodApiDetailsMapper;

    @GetMapping(value = MEXICAN_MONTH)
    public ResponseEntity<List<FoodApiMeal>> mexicanMonth(@PathVariable Integer pageNumber) {
        List<FoodApiMeal> meals = foodApiService.getFoodMealPage(pageNumber);
        return ResponseEntity
                .ok(meals);
    }

    @GetMapping(value = MEXICAN_MONTH_RECEIPT)
    public ResponseEntity<FoodApiMealDetailsDTO> mexicanMonthReceipt(@PathVariable Integer mealId) {
        FoodApiMealDetailsDTO meal = foodApiDetailsMapper.map(foodApiService.getFoodApiMealDetails(mealId));
        return ResponseEntity
                .ok(meal);
    }
}
