package food_app.bussines;

import Food_app.api.dto.mapper.OrderDetailsMapper;
import Food_app.business.CustomerService;
import Food_app.business.MealService;
import Food_app.business.OrderService;
import Food_app.business.RestaurantService;
import Food_app.business.dao.OrderDAO;
import Food_app.domain.Order;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;


    @Mock
    private OrderDAO orderDAO;
    @Mock
    private CustomerService customerService;
    @Mock
    private RestaurantService restaurantService;
    @Mock
    private OrderDetailsMapper orderDetailsMapper;
    @Mock
    private MealService mealService;

    @Test
    public void testCancelOrderOrderExistsAndCanBeCancelledShouldCancelOrder() {
        String orderNumber = "123456";
        OffsetDateTime receivedDateTime = OffsetDateTime.now().minusMinutes(15);
        Order order = SomeFixtures.someOrder().withReceivedDateTime(receivedDateTime);

        when(orderDAO.findByOrderNumber(orderNumber)).thenReturn(Optional.of(order));

        orderService.cancelOrder(orderNumber);

        verify(orderDAO).findByOrderNumber(orderNumber);
        verify(orderDAO).cancelOrder(order);
    }

    @Test()
    public void testCancelOrderOrderExistsButTooLateToCancelShouldThrowRuntimeException() {
        String orderNumber = "123456";
        OffsetDateTime receivedDateTime = OffsetDateTime.now().minusMinutes(25);
        Order order = SomeFixtures.someOrder()
                .withReceivedDateTime(receivedDateTime)
                .withComplete(false);

        when(orderDAO.findByOrderNumber(orderNumber)).thenReturn(Optional.of(order));

        assertThrows(RuntimeException.class,()->orderService.cancelOrder(orderNumber));

        verify(orderDAO).findByOrderNumber(orderNumber);

    }

    @Test()
    public void testCancelOrderOrderDoesNotExistShouldThrowEntityNotFoundException() {
        String orderNumber = "123456";

        when(orderDAO.findByOrderNumber(orderNumber)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,()->orderService.cancelOrder(orderNumber));

        verify(orderDAO).findByOrderNumber(orderNumber);
    }
}

