package food_app.api.dto.mapper;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.api.dto.mapper.*;
import Food_app.domain.Meal;
import Food_app.domain.Restaurant;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class restaurantMenuMapperTest {

    private RestaurantMenuMapper restaurantMenuMapper;
    @BeforeEach
    public void setUp() {
        restaurantMenuMapper = Mappers.getMapper(RestaurantMenuMapper.class);
    }
    @Test
    public void testMap() {

        Restaurant restaurant = SomeFixtures.someRestaurant();

        RestaurantMenuDTO result = restaurantMenuMapper.map(restaurant);

        assertEquals(restaurant.getDescription(),result.getDescription());
        assertEquals(restaurant.getPhone(),result.getPhone());
        assertEquals(restaurant.getMeals().size(),result.getMeals().size());
        assertEquals(restaurant.getFoodCategory(),result.getFoodCategory());
        assertEquals(restaurant.getFoodCategory(),result.getFoodCategory());
    }

}
