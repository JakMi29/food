package Food_app.api.dto.mapper;

import Food_app.api.dto.MealDTO;
import Food_app.domain.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealMapper extends BigDecimalMapper {

    @Mapping(source = "price", target = "price", qualifiedByName = "stringToBigDecimalMapper")
    Meal map(final MealDTO meal);
}
