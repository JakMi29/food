package Food_app.api.dto.mapper;

import Food_app.api.dto.MealDTO;
import Food_app.domain.Meal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class MealMapperImpl implements MealMapper {

    @Override
    public Meal map(MealDTO meal) {
        if ( meal == null ) {
            return null;
        }

        Meal.MealBuilder meal1 = Meal.builder();

        meal1.price( StringToBigDecimalMapper( meal.getPrice() ) );
        meal1.name( meal.getName() );
        meal1.category( meal.getCategory() );
        meal1.description( meal.getDescription() );
        meal1.image( meal.getImage() );

        return meal1.build();
    }
}
