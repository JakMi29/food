package food_app.infrastructure.database.repository.mapper;

import Food_app.domain.RestaurantStreet;
import Food_app.infrastructure.database.entity.RestaurantStreetEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantStreetEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RestaurantStreetEntityMapperTest {

    private RestaurantStreetEntityMapper restaurantStreetEntityMapper;
    @BeforeEach
    public void setUp() {
        restaurantStreetEntityMapper = Mappers.getMapper(RestaurantStreetEntityMapper.class);
    }
    @Test
    void TestEntityToRestaurantStreet() {
        RestaurantStreetEntity restaurantStreetEntity = SomeFixtures.someRestaurantStreetEntity();


        RestaurantStreet restaurantStreet  = restaurantStreetEntityMapper.mapWithRestaurant(restaurantStreetEntity);


        assertEquals(restaurantStreetEntity.getStreet().getName(), restaurantStreet.getStreet().getName());
        assertEquals(restaurantStreetEntity.getRestaurant().getName(), restaurantStreet.getRestaurant().getName());
        assertEquals(restaurantStreetEntity.getRestaurantStreetId(), restaurantStreet.getRestaurantStreetId());
        restaurantStreetEntity = null;
        restaurantStreet  = restaurantStreetEntityMapper.map(restaurantStreetEntity);
        assertNull(restaurantStreet);
    }


}
