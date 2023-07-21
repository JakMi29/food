package Food_app.api.dto.mapper;

import Food_app.api.dto.RestaurantDTO;
import Food_app.domain.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantDTO map(final Restaurant restaurant);


}
