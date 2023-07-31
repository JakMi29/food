package food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RestaurantEntityMapperTest {

    private RestaurantEntityMapper restaurantEntityMapper;
    @BeforeEach
    public void setUp() {
        restaurantEntityMapper = Mappers.getMapper(RestaurantEntityMapper.class);
    }
    @Test
    void TestRestaurantToEntity() {
        // Given
        Restaurant restaurant = SomeFixtures.someRestaurant();

        // When
        RestaurantEntity restaurantEntity = restaurantEntityMapper.map(restaurant);

        // Then
        assertEquals(restaurantEntity.getRestaurantId(), restaurant.getRestaurantId());
        assertEquals(restaurantEntity.getName(), restaurant.getName());
        assertEquals(restaurantEntity.getDescription(), restaurant.getDescription());
        assertEquals(restaurantEntity.getEmail(), restaurant.getEmail());
        assertEquals(restaurantEntity.getFoodCategory(), restaurant.getFoodCategory());
    }

    @Test
    void TestEntityToRestaurant() {
        // Given
        RestaurantEntity restaurantEntity = SomeFixtures.someRestaurantEntity();

        // When
        Restaurant restaurant = restaurantEntityMapper.map(restaurantEntity);

        // Then
        assertEquals(restaurantEntity.getRestaurantId(), restaurant.getRestaurantId());
        assertEquals(restaurantEntity.getName(), restaurant.getName());
        assertEquals(restaurantEntity.getDescription(), restaurant.getDescription());
        assertEquals(restaurantEntity.getEmail(), restaurant.getEmail());
        assertEquals(restaurantEntity.getFoodCategory(), restaurant.getFoodCategory());
    }
    @Test
    void mapCustomerWithSomeNullsToEntityTest() {
        // Given
        Restaurant restaurant = Restaurant.builder().build();

        // When
        RestaurantEntity restaurantEntity = restaurantEntityMapper.map(restaurant);

        // Then
        assertNull(restaurantEntity.getRestaurantId());
        assertNull(restaurantEntity.getPhone());
        assertNull(restaurantEntity.getMeals());
        assertNull(restaurantEntity.getName());
        assertNull(restaurantEntity.getDescription());

        restaurant=null;
        restaurantEntity=restaurantEntityMapper.map(restaurant);
        assertNull(restaurantEntity);
    }

    @Test
    void mapEntityWithSomeNullsToRestaurantTest() {

        // When
        RestaurantEntity restaurantEntity = RestaurantEntity.builder()
                .orders(Set.of())
                .build();

        // When
        Restaurant restaurant = restaurantEntityMapper.mapWithOrders(restaurantEntity);

        // Then
        assertNull(restaurant.getRestaurantId());
        assertNull(restaurant.getPhone());
        assertNull(restaurant.getMeals());
        assertNull(restaurant.getName());
        assertNull(restaurant.getDescription());

        restaurantEntity=null;
        restaurant=restaurantEntityMapper.map(restaurantEntity);
        assertNull(restaurant);
    }
}
