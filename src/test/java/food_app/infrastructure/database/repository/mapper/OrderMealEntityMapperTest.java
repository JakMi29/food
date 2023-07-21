package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Address;
import Food_app.domain.OrderMeal;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.OrderMealEntity;
import Food_app.infrastructure.database.repository.mapper.AddressEntityMapper;
import Food_app.infrastructure.database.repository.mapper.OrderMealMapper;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMealEntityMapperTest {

    private OrderMealMapper orderMealMapper;
    @BeforeEach
    public void setUp() {
        orderMealMapper = Mappers.getMapper(OrderMealMapper.class);
    }
    @Test
    void MapAddressToAddressEntityCorrectly() {
        // Given
        OrderMealEntity orderMealEntity = SomeFixtures.someOrderMealEntity();

        // When
        OrderMeal orderMeal = orderMealMapper.map(orderMealEntity);

        // Then

        assertEquals(orderMeal.getOrderMealId(), orderMealEntity.getOrderMealId());
        assertEquals(orderMeal.getPrice(), orderMealEntity.getPrice());
        assertEquals(orderMeal.getQuantity(), orderMealEntity.getQuantity());
        assertEquals(orderMeal.getMeal().getMealId(), orderMealEntity.getMeal().getMealId());
        assertEquals(orderMeal.getMeal().getName(), orderMealEntity.getMeal().getName());
        assertEquals(orderMeal.getMeal().getCategory(), orderMealEntity.getMeal().getCategory());
        assertEquals(orderMeal.getMeal().getImage(), orderMealEntity.getMeal().getImage());
    }

}
