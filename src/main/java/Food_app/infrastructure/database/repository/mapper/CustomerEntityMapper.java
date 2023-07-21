package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Customer;
import Food_app.domain.Order;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.CustomerEntity;
import Food_app.infrastructure.database.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerEntityMapper {
    @Mapping(target = "orders", ignore = true)
    CustomerEntity map(Customer meal);

    @Mapping(target = "orders", ignore = true)
    Customer map(CustomerEntity mealEntity);


    @Named("mapWithOrders")
    default Customer mapWithOrders(CustomerEntity customerEntity) {
        Customer customer = map(customerEntity);
        return customer.withOrders(
                customerEntity.getOrders().stream()
                        .map(this::orderBuilder)
                        .collect(Collectors.toSet()));
    }

    default Order orderBuilder(OrderEntity order) {
        return Order.builder()
                .orderId(order.getOrderId())
                .price(order.getPrice())
                .orderNumber(order.getOrderNumber())
                .completedDateTime(order.getCompletedDateTime())
                .receivedDateTime(order.getReceivedDateTime())
                .restaurant(
                        Restaurant.builder()
                                .name(order.getRestaurant().getName())
                                .foodCategory(order.getRestaurant().getFoodCategory())
                                .build()
                )
                .complete(order.getComplete())
                .build();
    }

}
