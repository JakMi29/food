package Food_app.api.controller.customer;

import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.business.FoodApiService;
import Food_app.domain.FoodApiMeal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerMexicanMonthController {
    public static final String CUSTOMER_MEXICAN_MONTH = "/customer/{name}/mexicanMonth/{pageNumber}";
    public static final String CUSTOMER_MEXICAN_MONTH_RECEIPT = "/customer/{name}/mexicanMonth/receipt/{id}";
    public static final String CUSTOMER_MEXICAN_MONTH_PAGE = "customer/customer_mexican_month";
    public static final String CUSTOMER_MEXICAN_MONTH_RECEIPT_PAGE = "customer/customer_mexican_month_receipt";
    private final FoodApiService foodApiService;
    private final FoodApiDetailsMapper foodApiDetailsMapper;
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
}
