package Food_app.api.controller.restaurant;

import Food_app.api.dto.OrderDetailsDTO;
import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.RestaurantOrderDTO;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.business.OrderService;
import Food_app.business.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class RestaurantOrderController {
    public static final String RESTAURANT_ORDER_DETAILS = "/restaurant/{name}/orderDetails/{orderNumber}";
    public static final String RESTAURANT_ORDER_DETAILS_PAGE = "restaurant/restaurant_order_details";

    public static final String RESTAURANT_ORDERS = "/restaurant/{name}/orders";
    public static final String RESTAURANT_REDIRECT_ORDERS = "redirect:/restaurant/%s/orders";
    public static final String RESTAURANT_ORDERS_PAGE = "restaurant/restaurantOrderHistory";
    public static final String RESTAURANT_COMPLETE_ORDER = "/restaurant/{name}/completeOrder/{orderNumber}";
    private final OrderService orderService;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantService restaurantService;

    @GetMapping(value = RESTAURANT_ORDER_DETAILS)
    public String orderDetails(@PathVariable String name, @PathVariable String orderNumber, Model model) {
        OrderDetailsDTO order = orderService.getOrderDetails(orderNumber);
        model.addAttribute("order", order);
        model.addAttribute("name", name);
        return RESTAURANT_ORDER_DETAILS_PAGE;
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
}
