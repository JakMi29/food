package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Meal;
import Food_app.infrastructure.database.entity.MealEntity;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MealEntityMapper {

    @Mapping(target = "restaurant", ignore = true)
    MealEntity map(Meal meal);

    @Mapping(target = "restaurant", ignore = true)
    Meal map(MealEntity mealEntity);

    default MealEntity mapWithRestaurant(Meal meal) {
        MealEntity mealEntity = map(meal);
        mealEntity.setRestaurant(RestaurantEntity.builder()
                .restaurantId(meal.getRestaurant().getRestaurantId())
                .phone(meal.getRestaurant().getPhone())
                .email(meal.getRestaurant().getEmail())
                .name(meal.getRestaurant().getName())
                .foodCategory(meal.getRestaurant().getFoodCategory())
                .build());
        return mealEntity;
    }


}
