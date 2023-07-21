package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.OrderMeal;
import Food_app.infrastructure.database.entity.OrderMealEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMealMapper {
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "meal.restaurant", ignore = true)
    OrderMeal map(OrderMealEntity order);
}
