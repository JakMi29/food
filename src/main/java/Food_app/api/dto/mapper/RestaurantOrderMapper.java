package Food_app.api.dto.mapper;

import Food_app.api.dto.RestaurantOrderDTO;
import Food_app.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantOrderMapper extends SomeMappers {


    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToStringMapper")
    RestaurantOrderDTO map(final Order order);
}
