package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Meal;
import Food_app.domain.Order;
import Food_app.infrastructure.database.entity.MealEntity;
import Food_app.infrastructure.database.entity.OrderEntity;
import Food_app.infrastructure.database.repository.mapper.MealEntityMapper;
import Food_app.infrastructure.database.repository.mapper.OrderEntityMapper;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
