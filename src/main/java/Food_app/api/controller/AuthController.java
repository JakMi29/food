package Food_app.api.controller;

import Food_app.api.dto.CreateCustomerDTO;
import Food_app.api.dto.CreateRestaurantOwnerDTO;
import Food_app.business.CustomerService;
import Food_app.business.RestaurantOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class AuthController {
    public static final String REGISTRATION = "/registration";
    public static final String REGISTRATION_CUSTOMER = "/registration/customer";
    public static final String REGISTRATION_RESTAURANT_OWNER = "/registration/restaurantOwner";
    public static final String HOME = "/";
    public static final String HOME_PAGE = "home";
    public static final String REGISTRATION_PAGE = "registration_home_page";
    public static final String CREATE_CUSTOMER = "create_customer";
    public static final String REDIRECT_REGISTRATION = "redirect:/registration";
    public static final String CREATE_RESTAURANT_OWNER = "create_restaurant_owner";

    private final CustomerService customerService;
    private final RestaurantOwnerService restaurantOwnerService;


    @GetMapping(value = HOME)
    public String home(Model model) {
        return HOME_PAGE;
    }

    @GetMapping(value = REGISTRATION)
    public String registrationForm(Model model) {
        return REGISTRATION_PAGE;
    }

    @GetMapping(value = REGISTRATION_CUSTOMER)
    public String registerCustomerForm(Model model) {
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO();
        model.addAttribute("createCustomerDTO", createCustomerDTO);
        return CREATE_CUSTOMER;
    }

    @PostMapping(value = REGISTRATION_CUSTOMER)
    public String registerCustomer(@Valid @ModelAttribute("customer") CreateCustomerDTO createCustomerDTO) {
        customerService.createCustomer(createCustomerDTO);
        return REDIRECT_REGISTRATION;
    }

    @GetMapping(value = REGISTRATION_RESTAURANT_OWNER)
    public String registerRestaurantOwnerForm(Model model) {
        CreateRestaurantOwnerDTO createRestaurantOwnerDTO = new CreateRestaurantOwnerDTO();
        model.addAttribute("createRestaurantOwnerDTO", createRestaurantOwnerDTO);
        return CREATE_RESTAURANT_OWNER;
    }


    @PostMapping(value = REGISTRATION_RESTAURANT_OWNER)
    public String registerRestaurantOwner(
            @Valid @ModelAttribute("restaurantOwner") CreateRestaurantOwnerDTO createRestaurantOwnerDTO) {
        restaurantOwnerService.createRestaurantOwner(createRestaurantOwnerDTO);
        return REDIRECT_REGISTRATION;
    }
}
