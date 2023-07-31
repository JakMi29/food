package Food_app.api.controller.customer;

import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.business.CustomerService;
import Food_app.business.RestaurantPaginationService;
import Food_app.business.RestaurantService;
import Food_app.domain.Customer;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_HOME = "/customer";

    public static final String CUSTOMER_RESTAURANTS = "/customer/{name}/restaurants/{pageNumber}/{pageSize}";
    public static final String CUSTOMER_RESTAURANTS_PAGE = "customer/customer_restaurants";
    public static final String CUSTOMER_RESTAURANTS_BY_STREET = "/customer/{name}/restaurantsByStreet";
    public static final String CUSTOMER_RESTAURANT_MENU = "/customer/{name}/restaurantMenu/{restaurantName}";

    public static final String CUSTOMER_RESTAURANTS_BY_STREET_PAGE = "customer/customer_restaurants_by_street";
    public static final String CUSTOMER_RESTAURANT_MENU_PAGE = "customer/customer_restaurant_menu";
    public static final String CUSTOMER_HOME_PAGE = "customer/customer_home_page";

    private final UserRepository userRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;
    private final RestaurantMenuMapper restaurantMenuMapper;
    private final CustomerService customerService;
    private final RestaurantPaginationService restaurantPaginationService;



    @GetMapping(value = CUSTOMER_HOME)
    public String homePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByUserName(userDetails.getUsername());
        Customer customer = customerService.findCustomerByUserName(user.getUserName());
        model.addAttribute("name", customer.getUserName());
        return CUSTOMER_HOME_PAGE;
    }



    @GetMapping(value = CUSTOMER_RESTAURANTS)
    public String restaurants(@PathVariable String name, @PathVariable Integer pageNumber,@PathVariable Integer pageSize, Model model) {
        Page<RestaurantEntity> page = restaurantPaginationService.paginate(pageNumber, pageSize);
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

    @GetMapping(value = CUSTOMER_RESTAURANT_MENU)
    public String restaurantMenu(@PathVariable String name, @PathVariable String restaurantName, Model model) {
        RestaurantMenuDTO restaurantDTO = restaurantMenuMapper.map(restaurantService.findRestaurantByNameWithMeals(restaurantName));
        model.addAttribute("name", name);
        model.addAttribute("restaurantName", restaurantName);
        model.addAttribute("restaurant", restaurantDTO);
        return CUSTOMER_RESTAURANT_MENU_PAGE;
    }
}
