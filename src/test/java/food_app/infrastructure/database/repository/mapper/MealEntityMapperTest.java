package food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Meal;
import Food_app.infrastructure.database.entity.MealEntity;
import Food_app.infrastructure.database.repository.mapper.MealEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MealEntityMapperTest {

    private MealEntityMapper mapper;
    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(MealEntityMapper.class);
    }
    @Test
    void testMapMealToMealEntity() {
        // Given
        Meal meal = SomeFixtures.someMeal();

        // When
        MealEntity mealEntity = mapper.mapWithRestaurant(meal);

        // Then
        assertEquals(meal.getMealId(), mealEntity.getMealId());
        assertEquals(meal.getPrice(), mealEntity.getPrice());
        assertEquals(meal.getImage(), mealEntity.getImage());
        assertEquals(meal.getDescription(), mealEntity.getDescription());
        assertEquals(meal.getName(), mealEntity.getName());
        assertEquals(meal.getRestaurant().getRestaurantId(), mealEntity.getRestaurant().getRestaurantId());

    }

    @Test
    void testMapMealEntityToMeal() {
        MealEntity mealEntity = SomeFixtures.someMealEntity();

        // When
        Meal meal = mapper.map(mealEntity);

        // Then
        assertEquals(meal.getMealId(), mealEntity.getMealId());
        assertEquals(meal.getPrice(), mealEntity.getPrice());
        assertEquals(meal.getImage(), mealEntity.getImage());
        assertEquals(meal.getDescription(), mealEntity.getDescription());
        assertEquals(meal.getName(), mealEntity.getName());
    }
    @Test
    void testMapMealEntityWithSomeNullsToMeal() {
        MealEntity mealEntity = MealEntity.builder()
                .restaurant(SomeFixtures.someRestaurantEntity())
                .build();

        // When
        Meal meal = mapper.map(mealEntity);

        // Then
        assertEquals(meal.getMealId(), mealEntity.getMealId());
        assertEquals(meal.getPrice(), mealEntity.getPrice());
        assertEquals(meal.getImage(), mealEntity.getImage());
        assertEquals(meal.getDescription(), mealEntity.getDescription());
        assertEquals(meal.getName(), mealEntity.getName());

        mealEntity=null;
        meal=mapper.map(mealEntity);
        assertNull(meal);

        meal = Meal.builder()
                .restaurant(SomeFixtures.someRestaurant())
                .build();

        // When
        mealEntity = mapper.mapWithRestaurant(meal);

        // Then
        assertEquals(mealEntity.getMealId(), meal.getMealId());
        assertEquals(mealEntity.getPrice(), meal.getPrice());
        assertEquals(mealEntity.getImage(), meal.getImage());
        assertEquals(mealEntity.getDescription(), meal.getDescription());
        assertEquals(mealEntity.getName(), meal.getName());

    }
}
