package Food_app.api.dto.mapper;

import Food_app.api.dto.CustomerOrderDTO;
import Food_app.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerOrderMapper extends SomeMappers {


    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToStringMapper")
    @Mapping(source = "restaurant", target = "restaurantName", qualifiedByName = "restaurantToName")
    CustomerOrderDTO map(final Order order);

}
