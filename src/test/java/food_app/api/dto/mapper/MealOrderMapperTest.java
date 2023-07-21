package food_app.api.dto.mapper;

import Food_app.api.dto.OrderMealDTO;
import Food_app.api.dto.mapper.MealMapper;
import Food_app.api.dto.mapper.MealOrderMapper;
import Food_app.domain.OrderMeal;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealOrderMapperTest {

    private MealOrderMapper mealMapper;
    @BeforeEach
    public void setUp() {
        mealMapper = Mappers.getMapper(MealOrderMapper.class);
    }
    @Test
    void testMap() {
        OrderMeal orderMeal=SomeFixtures.someOrderMeal();

        OrderMealDTO meal=mealMapper.map(orderMeal);

        assertEquals(orderMeal.getQuantity(),meal.getQuantity());
        assertEquals(orderMeal.getPrice().toString(),meal.getPrice());

    }
}
