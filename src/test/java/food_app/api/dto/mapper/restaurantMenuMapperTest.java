package food_app.api.dto.mapper;

import Food_app.api.dto.RestaurantMenuDTO;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.domain.Restaurant;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

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
    @Test
    public void testMapWithNulls() {

        Restaurant restaurant = null;

        RestaurantMenuDTO result = restaurantMenuMapper.map(restaurant);

        assertNull(result);

    }


}
