package food_app.api.dto.mapper;

import Food_app.api.dto.MealDTO;
import Food_app.api.dto.StreetDTO;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.api.dto.mapper.MealMapper;
import Food_app.api.dto.mapper.MealMapperImpl;
import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Meal;
import Food_app.domain.Street;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealMapperTest {

    private MealMapper mealMapper;
    @BeforeEach
    public void setUp() {
        mealMapper = Mappers.getMapper(MealMapper.class);
    }
    @Test
    void testMap() {
        MealDTO mealDTO=SomeFixtures.someMealDTO();

        Meal meal=mealMapper.map(mealDTO);

        assertEquals(mealDTO.getName(),meal.getName());
        assertEquals(mealDTO.getDescription(),meal.getDescription());
        assertEquals(mealDTO.getPrice(),meal.getPrice().toString());
        assertEquals(mealDTO.getImage(),meal.getImage());
        assertEquals(mealDTO.getCategory(),meal.getCategory());

    }
}
