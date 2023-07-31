package food_app.api.dto.mapper;

import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.domain.Restaurant;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RestaurantMapperTest {

    private RestaurantMapper restaurantMapper;
    @BeforeEach
    public void setUp() {
        restaurantMapper = Mappers.getMapper(RestaurantMapper.class);
    }
    @Test
    public void testMap(){

        Restaurant restaurant1 = SomeFixtures.someRestaurant();

        RestaurantDTO result1 = restaurantMapper.map(restaurant1);

        assertEquals(restaurant1.getName(),result1.getName());
        assertEquals(restaurant1.getFoodCategory(),result1.getFoodCategory());
        assertEquals(restaurant1.getMeals().size(),result1.getMeals().size());
        assertEquals(restaurant1.getOrders().size(),result1.getOrders() .size());

        result1=restaurantMapper.map(null);
        assertNull(result1);
        Restaurant restaurant2=restaurant1.withMeals(null);
        RestaurantDTO result2=restaurantMapper.map(restaurant2);
        assertNull(result2.getMeals());

    }


}
