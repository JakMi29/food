package Food_app.api.dto.mapper;

import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.domain.FoodApiMealDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodApiDetailsMapper extends SomeMappers {


    FoodApiMealDetailsDTO map(final FoodApiMealDetails meal);
}
