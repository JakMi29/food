package Food_app.api.dto.mapper;

import Food_app.api.dto.OrderDetailsDTO;
import Food_app.api.dto.OrderMealDTO;
import Food_app.domain.Order;
import Food_app.domain.OrderMeal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailsMapper extends SomeMappers {

    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToStringMapper")
    @Mapping(source = "receivedDateTime", target = "receivedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "completedDateTime", target = "completedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "customer", target = "customer", qualifiedByName = "customerToName")
    @Mapping(source = "restaurant", target = "restaurant", qualifiedByName = "restaurantToName")
    OrderDetailsDTO map(final Order order);

    @Named("mapWithMeals")
    default OrderDetailsDTO mapWithMeals(Order order) {
        OrderDetailsDTO orderDetailsDTO = map(order);
        Set<OrderMeal> orderMeals = order.getOrderMeals();
        List<OrderMealDTO> meals = mapOrderMealEntities(orderMeals);
        orderDetailsDTO.setMeals(meals);
        return orderDetailsDTO;
    }

    default List<OrderMealDTO> mapOrderMealEntities(Set<OrderMeal> orderMealEntities) {
        return orderMealEntities.stream()
                .map(this::mapMeal)
                .collect(Collectors.toList());
    }

    default OrderMealDTO mapMeal(OrderMeal orderMeal) {
        return OrderMealDTO.builder()
                .name(orderMeal.getMeal().getName())
                .image(orderMeal.getMeal().getImage())
                .price(orderMeal.getPrice().toString())
                .quantity(orderMeal.getQuantity())
                .build();
    }
}
