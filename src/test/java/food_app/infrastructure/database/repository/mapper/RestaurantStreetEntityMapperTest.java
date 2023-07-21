package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.RestaurantStreet;
import Food_app.infrastructure.database.entity.RestaurantStreetEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantStreetEntityMapper;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantStreetEntityMapperTest {

    private RestaurantStreetEntityMapper restaurantStreetEntityMapper;
    @BeforeEach
    public void setUp() {
        restaurantStreetEntityMapper = Mappers.getMapper(RestaurantStreetEntityMapper.class);
    }
    @Test
    void TestEntityToRestaurantStreet() {
        // Given
        RestaurantStreetEntity restaurantStreetEntity = SomeFixtures.someRestaurantStreetEntity();

        // When
        RestaurantStreet restaurantStreet  = restaurantStreetEntityMapper.mapWithRestaurant(restaurantStreetEntity);

        // Then
        assertEquals(restaurantStreetEntity.getStreet().getName(), restaurantStreet.getStreet().getName());
        assertEquals(restaurantStreetEntity.getRestaurant().getName(), restaurantStreet.getRestaurant().getName());
        assertEquals(restaurantStreetEntity.getRestaurantStreetId(), restaurantStreet.getRestaurantStreetId());
    }


}
