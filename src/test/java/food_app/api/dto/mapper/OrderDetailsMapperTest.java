package food_app.api.dto.mapper;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.OrderDetailsDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OrderDetailsMapperTest {


    private OrderDetailsMapper orderDetailsMapper;
    @BeforeEach
    public void setUp() {
        orderDetailsMapper = Mappers.getMapper(OrderDetailsMapper.class);
    }
    @Test
    public void testMap(){

        Order order = SomeFixtures.someOrder();

        OrderDetailsDTO result = orderDetailsMapper.map(order);

        assertEquals(order.getOrderNumber(),result.getOrderNumber());
        assertEquals(order.getComplete(),result.getComplete());
        assertEquals(order.getCustomer().getName(),result.getCustomer());
        assertEquals(order.getRestaurant().getName(),result.getRestaurant());

    }

    @Test
    public void testMapWithMeals() {
        Order order = SomeFixtures.someOrder();

        OrderDetailsDTO result = orderDetailsMapper.mapWithMeals(order);

        assertEquals(order.getOrderNumber(),result.getOrderNumber());
        assertEquals(order.getComplete(),result.getComplete());
        assertEquals(order.getCustomer().getName(),result.getCustomer());
        assertEquals(order.getRestaurant().getName(),result.getRestaurant());
        assertEquals(order.getOrderMeals().size(),result.getMeals().size());
    }
}
