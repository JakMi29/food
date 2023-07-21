package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Address;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.AddressEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
