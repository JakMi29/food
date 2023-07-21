package food_app.api.dto.mapper;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.CustomerOrderDTO;
import Food_app.api.dto.mapper.*;
import Food_app.domain.Meal;
import Food_app.domain.Order;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerOrderMapperTest {

    @Autowired
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
        MealMapper mapper = new MealMapperImpl();

        Meal result = mapper.map(null);

        assertNull(result);
    }
}
