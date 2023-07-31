package food_app.infrastructure.database.repository.mapper;

import Food_app.domain.OrderMeal;
import Food_app.infrastructure.database.entity.OrderMealEntity;
import Food_app.infrastructure.database.repository.mapper.OrderMealMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        orderMealEntity = null;


        orderMeal = orderMealMapper.map(orderMealEntity);

        assertNull(orderMeal);
    }

}
