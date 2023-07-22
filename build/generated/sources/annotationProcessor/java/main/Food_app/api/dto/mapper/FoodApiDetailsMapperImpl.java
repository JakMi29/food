package Food_app.api.dto.mapper;

import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.domain.FoodApiMealDetails;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:39+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class FoodApiDetailsMapperImpl implements FoodApiDetailsMapper {

    @Override
    public FoodApiMealDetailsDTO map(FoodApiMealDetails meal) {
        if ( meal == null ) {
            return null;
        }

        FoodApiMealDetailsDTO.FoodApiMealDetailsDTOBuilder foodApiMealDetailsDTO = FoodApiMealDetailsDTO.builder();

        if ( meal.getId() != null ) {
            foodApiMealDetailsDTO.id( String.valueOf( meal.getId() ) );
        }
        foodApiMealDetailsDTO.title( meal.getTitle() );
        foodApiMealDetailsDTO.difficulty( meal.getDifficulty() );
        foodApiMealDetailsDTO.portion( meal.getPortion() );
        foodApiMealDetailsDTO.time( meal.getTime() );
        foodApiMealDetailsDTO.description( meal.getDescription() );
        List<String> list = meal.getIngredients();
        if ( list != null ) {
            foodApiMealDetailsDTO.ingredients( new ArrayList<String>( list ) );
        }
        Map<Integer, String> map = meal.getMethod();
        if ( map != null ) {
            foodApiMealDetailsDTO.method( new LinkedHashMap<Integer, String>( map ) );
        }
        foodApiMealDetailsDTO.image( meal.getImage() );

        return foodApiMealDetailsDTO.build();
    }
}
