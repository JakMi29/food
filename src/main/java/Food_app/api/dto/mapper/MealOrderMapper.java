package Food_app.api.dto.mapper;

import Food_app.api.dto.OrderMealDTO;
import Food_app.domain.OrderMeal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MealOrderMapper extends BigDecimalMapper {

    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToStringMapper")
    OrderMealDTO map(final OrderMeal meal);
}
