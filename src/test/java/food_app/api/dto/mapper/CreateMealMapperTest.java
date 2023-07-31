package food_app.api.dto.mapper;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.mapper.CreateMealMapper;
import Food_app.domain.Meal;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateMealMapperTest {


    private CreateMealMapper createMealMapper;

    @BeforeEach
    public void setUp() {
        createMealMapper = Mappers.getMapper(CreateMealMapper.class);
    }

    @Test
    public void testMap() throws IOException {

        CreateMealDTO mealDTO = SomeFixtures.someCreateMealDTO();

        Meal result = createMealMapper.map(mealDTO);

        assertEquals(mealDTO.getDescription(), result.getDescription());
        assertEquals(mealDTO.getCategory(), result.getCategory());
        assertEquals(mealDTO.getName(), result.getName());
        assertEquals(mealDTO.getName(), result.getName());
    }

    @Test
    public void testMapWithNull() {
        Meal result = createMealMapper.map(null);
        assertNull(result);
    }
}
