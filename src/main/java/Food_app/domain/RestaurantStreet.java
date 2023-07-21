package Food_app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@Value
@With
@Builder
@EqualsAndHashCode
public class RestaurantStreet {

    Integer restaurantStreetId;
    Street street;
    Restaurant restaurant;
}
