package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantStreet;
import Food_app.infrastructure.database.entity.RestaurantStreetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantStreetEntityMapper {


    @Mapping(target = "restaurant", ignore = true)
    RestaurantStreet map(RestaurantStreetEntity streetEntity);

    default RestaurantStreet mapWithRestaurant(RestaurantStreetEntity streetEntity) {
        RestaurantStreet restaurantStreet = map(streetEntity);
        return restaurantStreet.withRestaurant(Restaurant.builder()
                .restaurantId(streetEntity.getRestaurant().getRestaurantId())
                .name(streetEntity.getRestaurant().getName())
                .foodCategory(streetEntity.getRestaurant().getFoodCategory())
                .phone(streetEntity.getRestaurant().getPhone())
                .build());
    }


}
