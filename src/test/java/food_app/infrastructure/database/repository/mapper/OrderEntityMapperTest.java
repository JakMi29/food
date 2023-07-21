package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Address;
import Food_app.domain.Order;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.OrderEntity;
import Food_app.infrastructure.database.repository.mapper.AddressEntityMapper;
import Food_app.infrastructure.database.repository.mapper.OrderEntityMapper;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderEntityMapperTest {

    private OrderEntityMapper mapper;
    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(OrderEntityMapper.class);
    }
    @Test
    void testMapOrderToOrderEntity() {
        // Given
        Order order = SomeFixtures.someOrder();

        // When
        OrderEntity orderEntity = mapper.mapAllOrder(order);

        // Then
        assertEquals(order.getOrderId(), orderEntity.getOrderId());
        assertEquals(order.getPrice(), orderEntity.getPrice());
        assertEquals(order.getOrderNumber(), orderEntity.getOrderNumber());
        assertEquals(order.getComplete(), orderEntity.getComplete());
        assertEquals(order.getCompletedDateTime(), orderEntity.getCompletedDateTime());
        assertEquals(order.getReceivedDateTime(), orderEntity.getReceivedDateTime());
        assertEquals(order.getRestaurant().getName(), orderEntity.getRestaurant().getName());
        assertEquals(order.getCustomer().getName(), orderEntity.getCustomer().getName());
    }

    @Test
    void testMapOrderEntityToOrder() {
        OrderEntity order = SomeFixtures.someOrderEntity();

        // When
        Order orderEntity = mapper.mapWithRestaurantAndCustomer(order);

        // Then
        assertEquals(order.getOrderId(), orderEntity.getOrderId());
        assertEquals(order.getPrice(), orderEntity.getPrice());
        assertEquals(order.getOrderNumber(), orderEntity.getOrderNumber());
        assertEquals(order.getComplete(), orderEntity.getComplete());
        assertEquals(order.getCompletedDateTime(), orderEntity.getCompletedDateTime());
        assertEquals(order.getReceivedDateTime(), orderEntity.getReceivedDateTime());
        assertEquals(order.getRestaurant().getName(), orderEntity.getRestaurant().getName());
        assertEquals(order.getCustomer().getName(), orderEntity.getCustomer().getName());
    }
}
