package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Meal;
import Food_app.infrastructure.database.entity.MealEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class MealEntityMapperImpl implements MealEntityMapper {

    @Override
    public MealEntity map(Meal meal) {
        if ( meal == null ) {
            return null;
        }

        MealEntity.MealEntityBuilder mealEntity = MealEntity.builder();

        mealEntity.mealId( meal.getMealId() );
        mealEntity.name( meal.getName() );
        mealEntity.price( meal.getPrice() );
        mealEntity.category( meal.getCategory() );
        mealEntity.description( meal.getDescription() );
        mealEntity.image( meal.getImage() );

        return mealEntity.build();
    }

    @Override
    public Meal map(MealEntity mealEntity) {
        if ( mealEntity == null ) {
            return null;
        }

        Meal.MealBuilder meal = Meal.builder();

        meal.mealId( mealEntity.getMealId() );
        meal.name( mealEntity.getName() );
        meal.price( mealEntity.getPrice() );
        meal.category( mealEntity.getCategory() );
        meal.description( mealEntity.getDescription() );
        meal.image( mealEntity.getImage() );

        return meal.build();
    }
}
