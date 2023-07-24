package Food_app.business;

import Food_app.api.dto.OrderDetailsDTO;
import Food_app.api.dto.mapper.OrderDetailsMapper;
import Food_app.business.dao.OrderDAO;
import Food_app.domain.Customer;
import Food_app.domain.Order;
import Food_app.domain.OrderMeal;
import Food_app.domain.Restaurant;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final OrderDetailsMapper orderDetailsMapper;
    private final MealService mealService;

    @Transactional
    public void completeOrder(String orderName) {
        orderDAO.completeOrder(orderName);
    }

    @Transactional
    public OrderDetailsDTO getOrderDetails(String orderNumber) {
        Order order = orderDAO.findByOrderNumberWithDetails(orderNumber);
        return orderDetailsMapper.mapWithMeals(order);
    }

    @Transactional
    public void cancelOrder(String orderNumber) {
        Order order = orderDAO.findByOrderNumber(orderNumber).orElseThrow(EntityNotFoundException::new);
        System.out.println(OffsetDateTime.now());
        System.out.println(order.getReceivedDateTime());
        if (!order.getReceivedDateTime().plusMinutes(20).isAfter(OffsetDateTime.now()) && !order.getComplete()) {
            throw new RuntimeException("There is too late to cancel this order");
        }
        orderDAO.cancelOrder(order);
    }

    @Transactional
    public void createOrder(String customerName, String restaurantName, Map<String, String> mapOfMeals) {
        Customer customer = customerService.findCustomerByUserName(customerName);
        Restaurant restaurant = restaurantService.findRestaurantByNameWithOrders(restaurantName);
        Set<OrderMeal> orderMeals = prepareOrderMeals(mapOfMeals, restaurantName);
        if (orderMeals.isEmpty())
            throw new RuntimeException("Can not order empty order, please select some meal");
        OffsetDateTime time = OffsetDateTime.now();
        Order order = Order.builder()
                .restaurant(restaurant)
                .complete(false)
                .orderNumber(OrderNumberGenerator.generateOrderNumber(time))
                .customer(customer)
                .receivedDateTime(time)
                .price(calculatePrice(orderMeals))
                .build();
        orderMeals = orderMeals.stream().map(c -> c.withOrder(order)).collect(Collectors.toSet());
        Order order1 = order.withOrderMeals(orderMeals);
        orderDAO.createOrder(order1);

    }

    private BigDecimal calculatePrice(Set<OrderMeal> orderMeals) {
        return orderMeals.stream()
                .map(meal -> meal.getMeal().getPrice().multiply(BigDecimal.valueOf((meal.getQuantity()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<OrderMeal> prepareOrderMeals(Map<String, String> mapOfMeals, String restaurantName) {
        return mapOfMeals.entrySet().stream()
                .filter(entry -> !entry.getValue().equals("0"))
                .map(entry->prepareOrder(entry,restaurantName))
                .collect(Collectors.toSet());
    }
    private OrderMeal prepareOrder(Map.Entry<String, String> entry, String restaurantName){
        var meal = mealService.findByNameAndRestaurantName(entry.getKey(), restaurantName);
        return OrderMeal.builder()
                .meal(meal)
                .quantity(Integer.parseInt(entry.getValue()))
                .price(meal.getPrice().multiply(new BigDecimal(entry.getValue())))
                .build();
    }

}
