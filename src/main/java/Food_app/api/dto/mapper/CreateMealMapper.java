package Food_app.api.dto.mapper;

import Food_app.api.dto.CreateMealDTO;
import Food_app.domain.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreateMealMapper extends BigDecimalMapper {

    @Mapping(source = "price", target = "price", qualifiedByName = "stringToBigDecimalMapper")
    @Mapping(target = "image", source = "image", ignore = true)
    Meal map(final CreateMealDTO meal);
}
