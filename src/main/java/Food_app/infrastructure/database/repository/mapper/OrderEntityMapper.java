package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.*;
import Food_app.infrastructure.database.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {


    @Named("mapAllOrder")
    default OrderEntity mapAllOrder(Order order) {
        OrderEntity orders = OrderEntity.builder()
                .orderId(order.getOrderId())
                .price(order.getPrice())
                .complete(order.getComplete())
                .orderNumber(order.getOrderNumber())
                .receivedDateTime(order.getReceivedDateTime())
                .completedDateTime(order.getCompletedDateTime())
                .restaurant(mapRestaurant(order.getRestaurant()))
                .customer(mapCustomer(order.getCustomer()))
                .build();
        Set<OrderMealEntity> orderMeals = mapOrderMeal(order.getOrderMeals());
        orderMeals.forEach(c -> c.setOrder(orders));
        orders.setOrderMeals(orderMeals);
        return orders;
    }

    @Named("mapWithRestaurantAndCustomer")
    default Order mapWithRestaurantAndCustomer(OrderEntity orderEntity) {
        return Order.builder()
                .orderId(orderEntity.getOrderId())
                .price(orderEntity.getPrice())
                .complete(orderEntity.getComplete())
                .orderNumber(orderEntity.getOrderNumber())
                .receivedDateTime(orderEntity.getReceivedDateTime())
                .completedDateTime(orderEntity.getCompletedDateTime())
                .restaurant(mapRestaurantEntity(orderEntity.getRestaurant()))
                .customer(mapCustomerEntity(orderEntity.getCustomer()))
                .orderMeals(mapOrderMealsEntities(orderEntity.getOrderMeals()))
                .build();
    }

    default Set<OrderMeal> mapOrderMealsEntities(Set<OrderMealEntity> orderMealEntities) {
        return orderMealEntities.stream()
                .map(this::mapMeal)
                .collect(Collectors.toSet());
    }

    default Set<OrderMealEntity> mapOrderMeal(Set<OrderMeal> orderMeals) {
        return orderMeals.stream()
                .map(this::mapMeal)
                .collect(Collectors.toSet());

    }


    default OrderMealEntity mapMeal(OrderMeal orderMeal) {
        return OrderMealEntity.builder()
                .meal(MealEntity.builder()
                        .name(orderMeal.getMeal().getName())
                        .mealId(orderMeal.getMeal().getMealId())
                        .category(orderMeal.getMeal().getCategory())
                        .description(orderMeal.getMeal().getDescription())
                        .price(orderMeal.getMeal().getPrice())
                        .image(orderMeal.getMeal().getImage())
                        .build())
                .order(OrderEntity.builder()
                        .orderNumber(orderMeal.getOrder().getOrderNumber())
                        .build())
                .price(orderMeal.getPrice())
                .quantity(orderMeal.getQuantity())
                .orderMealId(orderMeal.getOrderMealId())
                .build();
    }

    default OrderMeal mapMeal(OrderMealEntity orderMealEntity) {
        return OrderMeal.builder()
                .meal(Meal.builder()
                        .name(orderMealEntity.getMeal().getName())
                        .mealId(orderMealEntity.getMeal().getMealId())
                        .category(orderMealEntity.getMeal().getCategory())
                        .description(orderMealEntity.getMeal().getDescription())
                        .price(orderMealEntity.getMeal().getPrice())
                        .image(orderMealEntity.getMeal().getImage())
                        .build())
                .order(Order.builder()
                        .orderNumber(orderMealEntity.getOrder().getOrderNumber())
                        .build())
                .price(orderMealEntity.getPrice())
                .quantity(orderMealEntity.getQuantity())
                .orderMealId(orderMealEntity.getOrderMealId())
                .build();
    }

    default Customer mapCustomerEntity(CustomerEntity customerEntity) {
        return Customer.builder()
                .name(customerEntity.getName())
                .surname(customerEntity.getSurname())
                .userName(customerEntity.getUserName())
                .phone(customerEntity.getPhone())
                .email(customerEntity.getEmail())
                .build();
    }

    default CustomerEntity mapCustomer(Customer customer) {
        return CustomerEntity.builder()
                .name(customer.getName())
                .customerId(customer.getCustomerId())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
    }

    default Restaurant mapRestaurantEntity(RestaurantEntity restaurantEntity) {
        return Restaurant.builder()
                .name(restaurantEntity.getName())
                .foodCategory(restaurantEntity.getFoodCategory())
                .restaurantId(restaurantEntity.getRestaurantId())
                .build();
    }

    default RestaurantEntity mapRestaurant(Restaurant restaurant) {
        return RestaurantEntity.builder()
                .name(restaurant.getName())
                .foodCategory(restaurant.getFoodCategory())
                .restaurantId(restaurant.getRestaurantId())
                .build();
    }
}