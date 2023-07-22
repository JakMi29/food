package Food_app.api.dto.mapper;

import Food_app.api.dto.MealDTO;
import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.domain.Meal;
import Food_app.domain.Restaurant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class RestaurantMenuMapperImpl implements RestaurantMenuMapper {

    @Override
    public RestaurantMenuDTO map(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantMenuDTO.RestaurantMenuDTOBuilder restaurantMenuDTO = RestaurantMenuDTO.builder();

        restaurantMenuDTO.address( addressToString( restaurant.getAddress() ) );
        restaurantMenuDTO.name( restaurant.getName() );
        restaurantMenuDTO.foodCategory( restaurant.getFoodCategory() );
        restaurantMenuDTO.phone( restaurant.getPhone() );
        restaurantMenuDTO.description( restaurant.getDescription() );
        restaurantMenuDTO.meals( mealSetToMealDTOList( restaurant.getMeals() ) );

        return restaurantMenuDTO.build();
    }

    protected MealDTO mealToMealDTO(Meal meal) {
        if ( meal == null ) {
            return null;
        }

        MealDTO.MealDTOBuilder mealDTO = MealDTO.builder();

        mealDTO.name( meal.getName() );
        if ( meal.getPrice() != null ) {
            mealDTO.price( meal.getPrice().toString() );
        }
        mealDTO.category( meal.getCategory() );
        mealDTO.description( meal.getDescription() );
        mealDTO.image( meal.getImage() );

        return mealDTO.build();
    }

    protected List<MealDTO> mealSetToMealDTOList(Set<Meal> set) {
        if ( set == null ) {
            return null;
        }

        List<MealDTO> list = new ArrayList<MealDTO>( set.size() );
        for ( Meal meal : set ) {
            list.add( mealToMealDTO( meal ) );
        }

        return list;
    }
}
