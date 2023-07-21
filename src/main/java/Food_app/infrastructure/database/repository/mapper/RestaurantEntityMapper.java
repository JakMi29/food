package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Customer;
import Food_app.domain.Meal;
import Food_app.domain.Order;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {

    @Mapping(target = "meals", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Named("map")
    Restaurant map(RestaurantEntity restaurantEntity);

    @Mapping(target = "meals", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Named("map")
    RestaurantEntity map(Restaurant restaurantEntity);

    @Named("mapWithMeals")
    default Restaurant mapWithMeals(RestaurantEntity restaurantEntity) {
        Restaurant restaurant = map(restaurantEntity);
        return restaurant.withMeals(
                restaurantEntity.getMeals().stream().map(
                        c -> Meal.builder()
                                .restaurant(restaurant)
                                .mealId(c.getMealId())
                                .price(c.getPrice())
                                .name(c.getName())
                                .image(c.getImage())
                                .description(c.getDescription())
                                .category(c.getCategory())
                                .build()
                ).collect(Collectors.toSet())
        );
    }

    @Named("mapWithOrders")
    default Restaurant mapWithOrders(RestaurantEntity restaurantEntity) {
        Restaurant restaurant = map(restaurantEntity);
        return restaurant.withOrders(
                restaurantEntity.getOrders().stream().map(
                        c -> Order.builder()
                                .orderId(c.getOrderId())
                                .price(c.getPrice())
                                .orderNumber(c.getOrderNumber())
                                .completedDateTime(c.getCompletedDateTime())
                                .receivedDateTime(c.getReceivedDateTime())
                                .customer(
                                        Customer.builder()
                                                .name(c.getCustomer().getUserName())
                                                .build()
                                )
                                .complete(c.getComplete())
                                .build()
                ).collect(Collectors.toSet())
        );
    }


}
