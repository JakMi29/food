package Food_app.domain;

import Food_app.infrastructure.security.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@Value
@With
@Builder
@EqualsAndHashCode

public class RestaurantOwner {

    Integer restaurantOwnerId;
    Integer userId;
    String name;
    String surname;
    String userName;
    String phone;
    String email;
    User user;
}

