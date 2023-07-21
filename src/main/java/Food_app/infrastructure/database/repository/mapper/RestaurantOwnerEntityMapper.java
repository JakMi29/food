package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.RestaurantOwner;
import Food_app.infrastructure.database.entity.RestaurantOwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantOwnerEntityMapper {

    RestaurantOwner map(RestaurantOwnerEntity restaurantEntity);

    RestaurantOwnerEntity map(RestaurantOwner restaurantOwner);

}
