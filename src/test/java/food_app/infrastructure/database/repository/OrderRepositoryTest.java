package food_app.infrastructure.database.repository;

import Food_app.domain.Order;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.OrderEntity;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.OrderRepository;
import Food_app.infrastructure.database.repository.jpa.OrderJpaRepository;
import Food_app.infrastructure.database.repository.mapper.OrderEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryTest {
    @Mock
    private OrderJpaRepository mockJpaRepository;

    @Mock
    private RestaurantEntityMapper mockRestaurantEntityMapper;

    @Mock
    private OrderEntityMapper mockOrderEntityMapper;
    @InjectMocks
    private OrderRepository orderRepository;


    @Test
    void testFindByRestaurant() {
        Restaurant restaurant = SomeFixtures.someRestaurant();
        List<OrderEntity> entities = Collections.singletonList(SomeFixtures.someOrderEntity());
        List<Order> expectedOrders = Collections.singletonList(SomeFixtures.someOrder());

        when(mockRestaurantEntityMapper.map(restaurant)).thenReturn(SomeFixtures.someRestaurantEntity());
        when(mockJpaRepository.findAllByRestaurant(any(RestaurantEntity.class))).thenReturn(entities);
        when(mockOrderEntityMapper.mapWithRestaurantAndCustomer(any(OrderEntity.class))).thenReturn(SomeFixtures.someOrder());

        List<Order> result = orderRepository.findByRestaurant(restaurant);

        verify(mockJpaRepository, times(1)).findAllByRestaurant(any(RestaurantEntity.class));

        assertEquals(expectedOrders, result);
    }

    @Test
    void testFindByOrderNumberWithDetails() {
        String orderNumber = "123";
        OrderEntity orderEntity = SomeFixtures.someOrderEntity();
        Order expectedOrder = SomeFixtures.someOrder();

        when(mockJpaRepository.findByOrderNumberWithRelatedOrderMeals(orderNumber)).thenReturn(orderEntity);
        when(mockOrderEntityMapper.mapWithRestaurantAndCustomer(orderEntity)).thenReturn(expectedOrder);

        Order result = orderRepository.findByOrderNumberWithDetails(orderNumber);

        verify(mockJpaRepository, times(1)).findByOrderNumberWithRelatedOrderMeals(orderNumber);

        assertEquals(expectedOrder, result);
    }

    @Test
    void testFindByOrderNumber() {
        String orderNumber = "123";
        OrderEntity orderEntity = SomeFixtures.someOrderEntity();
        Order expectedOrder = SomeFixtures.someOrder();

        when(mockJpaRepository.findByOrderNumber(orderNumber)).thenReturn(Optional.of(orderEntity));
        when(mockOrderEntityMapper.mapWithRestaurantAndCustomer(orderEntity)).thenReturn(expectedOrder);

        Optional<Order> result = orderRepository.findByOrderNumber(orderNumber);

        verify(mockJpaRepository, times(1)).findByOrderNumber(orderNumber);

        assertEquals(Optional.of(expectedOrder), result);
    }

    @Test
    void testCompleteOrder() {
        String orderNumber = "123";
        OrderEntity orderEntity = SomeFixtures.someOrderEntity();

        when(mockJpaRepository.findByOrderNumber(orderNumber)).thenReturn(Optional.of(orderEntity));

        orderRepository.completeOrder(orderNumber);

        verify(mockJpaRepository, times(2)).findByOrderNumber(orderNumber);

        verify(mockJpaRepository, times(1)).save(orderEntity);

    }

    @Test
    void testCancelOrder() {
        Order order = SomeFixtures.someOrder();
        OrderEntity orderEntity = SomeFixtures.someOrderEntity();

        when(mockOrderEntityMapper.mapAllOrder(order)).thenReturn(orderEntity);

        orderRepository.cancelOrder(order);

        verify(mockJpaRepository, times(1)).delete(orderEntity);
    }

    @Test
    void testCreateOrder() {
        Order order = SomeFixtures.someOrder();
        OrderEntity orderEntity = SomeFixtures.someOrderEntity();

        when(mockOrderEntityMapper.mapAllOrder(order)).thenReturn(orderEntity);

        orderRepository.createOrder(order);

        verify(mockJpaRepository, times(1)).saveAndFlush(orderEntity);
    }
}