package Food_app.api.dto.mapper;

import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.domain.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMenuMapper extends SomeMappers {
    @Mapping(source = "address", target = "address", qualifiedByName = "addressToString")
    RestaurantMenuDTO map(final Restaurant restaurant);

}
