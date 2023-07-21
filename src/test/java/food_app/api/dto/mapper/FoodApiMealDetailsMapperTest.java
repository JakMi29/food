package food_app.api.dto.mapper;

import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.api.dto.mapper.CustomerOrderMapper;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.api.dto.mapper.MealMapper;
import Food_app.api.dto.mapper.MealMapperImpl;
import Food_app.domain.FoodApiMealDetails;
import Food_app.domain.Meal;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FoodApiMealDetailsMapperTest {


    private FoodApiDetailsMapper foodApiMealDetailsMapper;
    @BeforeEach
    public void setUp() {
        foodApiMealDetailsMapper = Mappers.getMapper(FoodApiDetailsMapper.class);
    }
    @Test
    public void testMap() throws IOException {

        FoodApiMealDetails foodApiMealDetails = SomeFixtures.someFoodApiDetails();

        FoodApiMealDetailsDTO result = foodApiMealDetailsMapper.map(foodApiMealDetails);

        assertEquals(foodApiMealDetails.getDescription(),result.getDescription());
        assertEquals(foodApiMealDetails.getDifficulty(),result.getDifficulty());
        assertEquals(foodApiMealDetails.getMethod(),result.getMethod());
        assertEquals(foodApiMealDetails.getImage(),result.getImage());
        assertEquals(foodApiMealDetails.getTitle(),result.getTitle());
        assertEquals(foodApiMealDetails.getIngredients(),result.getIngredients());
    }

    @Test
    public void testMapWithNull() {
        MealMapper mapper = new MealMapperImpl();

        Meal result = mapper.map(null);

        assertNull(result);
    }
}
