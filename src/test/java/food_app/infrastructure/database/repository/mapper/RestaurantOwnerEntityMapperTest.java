package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantOwner;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.entity.RestaurantOwnerEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantOwnerEntityMapperTest {

    private RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;
    @BeforeEach
    public void setUp() {
        restaurantOwnerEntityMapper = Mappers.getMapper(RestaurantOwnerEntityMapper.class);
    }
    @Test
    void TestRestaurantToEntity() {
        // Given
        RestaurantOwner restaurantOwner = SomeFixtures.someRestaurantOwner();

        // When
        RestaurantOwnerEntity restaurantOwnerEntity = restaurantOwnerEntityMapper.map(restaurantOwner);

        // Then
        assertEquals(restaurantOwnerEntity.getRestaurantOwnerId(), restaurantOwner.getRestaurantOwnerId());
        assertEquals(restaurantOwnerEntity.getName(), restaurantOwner.getName());
        assertEquals(restaurantOwnerEntity.getPhone(), restaurantOwner.getPhone());
        assertEquals(restaurantOwnerEntity.getEmail(), restaurantOwner.getEmail());
        assertEquals(restaurantOwnerEntity.getSurname(), restaurantOwner.getSurname());
        assertEquals(restaurantOwnerEntity.getUser().getUserName(), restaurantOwner.getUser().getUserName());
    }

    @Test
    void TestEntityToRestaurant() {
        // Given
        RestaurantOwnerEntity restaurantOwnerEntity = SomeFixtures.someRestaurantOwnerEntity();

        // When
        RestaurantOwner restaurantOwner = restaurantOwnerEntityMapper.map(restaurantOwnerEntity);

        // Then
        assertEquals(restaurantOwner.getRestaurantOwnerId(), restaurantOwnerEntity.getRestaurantOwnerId());
        assertEquals(restaurantOwner.getName(), restaurantOwnerEntity.getName());
        assertEquals(restaurantOwner.getPhone(), restaurantOwnerEntity.getPhone());
        assertEquals(restaurantOwner.getEmail(), restaurantOwnerEntity.getEmail());
        assertEquals(restaurantOwner.getSurname(), restaurantOwnerEntity.getSurname());
        assertEquals(restaurantOwner.getUser().getUserName(), restaurantOwnerEntity.getUser().getUserName());
    }
}
