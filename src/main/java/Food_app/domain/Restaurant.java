package Food_app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

import java.util.Set;

@Value
@With
@Builder
@EqualsAndHashCode(of = "name")
public class Restaurant {

    Integer restaurantId;
    String name;
    String foodCategory;
    String phone;
    String email;
    String description;
    Address address;
    RestaurantOwner restaurantOwner;
    Set<Meal> meals;
    Set<Order> orders;
}
