package Food_app.api.controller.customer;

import Food_app.api.dto.CustomerDTO;
import Food_app.api.dto.CustomerOrderDTO;
import Food_app.api.dto.OrderDetailsDTO;
import Food_app.api.dto.mapper.CustomerMapper;
import Food_app.business.CustomerService;
import Food_app.business.OrderService;
import Food_app.business.RestaurantPaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CustomerOrderController {
    public static final String CUSTOMER_ORDER_HISTORY = "/customer/{name}/orders";
    public static final String CREATE_ORDER = "/customer/{name}/order/{restaurantName}";
    public static final String CANCEL_ORDER = "/customer/{name}/cancelOrder/{orderNumber}";
    public static final String CUSTOMER_ORDER_DETAILS = "/customer/{name}/orderDetails/{orderNumber}";
    public static final String CUSTOMER_REDIRECT_RESTAURANTS_PAGE = "redirect:/customer/%s/restaurants/0/6";
    public static final String CUSTOMER_ORDERS_PAGE = "redirect:/customer/%s/orders";
    public static final String CUSTOMER_ORDER_DETAILS_PAGE = "customer/customer_order_details";
    public static final String CUSTOMER_ORDER_HISTORY_PAGE = "customer/customer_order_history";
    private final OrderService orderService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

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
