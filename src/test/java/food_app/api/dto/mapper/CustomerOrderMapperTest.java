package food_app.api.dto.mapper;

import Food_app.api.dto.CustomerOrderDTO;
import Food_app.api.dto.mapper.CustomerOrderMapper;
import Food_app.domain.Order;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerOrderMapperTest {


    private CustomerOrderMapper customerOrderMapper;

    @BeforeEach
    public void setUp() {
        customerOrderMapper = Mappers.getMapper(CustomerOrderMapper.class);
    }

    @Test
    public void testMap() throws IOException {
        Order order=SomeFixtures.someOrder();

        CustomerOrderDTO customerOrder=customerOrderMapper.map(order);

        assertEquals(customerOrder.getOrderNumber(),order.getOrderNumber());
        assertEquals(customerOrder.getPrice(),order.getPrice().toString());
        assertEquals(customerOrder.getRestaurantName(),order.getRestaurant().getName());
        assertTrue(customerOrder.getComplete());

    }

    @Test
    public void testMapWithNull() {
        Order order=null;

        CustomerOrderDTO customerOrder=customerOrderMapper.map(order);

        assertNull(customerOrder);
    }
}
