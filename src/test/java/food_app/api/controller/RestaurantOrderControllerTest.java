package food_app.api.controller;

import Food_app.api.controller.restaurant.RestaurantOrderController;
import Food_app.api.dto.OrderDetailsDTO;
import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.business.*;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.database.repository.mapper.UserEntityMapper;
import Food_app.infrastructure.security.UserRepository;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = RestaurantOrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantOrderControllerTest {
    @MockBean
    private final UserRepository userRepository;
    @MockBean
    private final UserEntityMapper userEntityMapper;
    @MockBean
    private final RestaurantOwnerService restaurantOwnerService;
    @MockBean
    private final RestaurantService restaurantService;
    @MockBean
    private final MealService mealService;
    @MockBean
    private final RestaurantMapper restaurantMapper;
    @MockBean
    private final RestaurantEntityMapper restaurantEntityMapper;
    @MockBean
    private final RestaurantMenuMapper restaurantMenuMapper;
    @MockBean
    private final OrderService orderService;
    @MockBean
    private final RestaurantStreetService restaurantStreetService;

    private MockMvc mockMvc;
    @Test
    void orderDetailsTest() throws Exception {
        // given
        String orderNumber = "orderNumber";
        String restaurantName = "Sushi Master";
        OrderDetailsDTO orderDetailsDTO = SomeFixtures.someOrderDetailsDto();
        when(orderService.getOrderDetails(orderNumber)).thenReturn(orderDetailsDTO);
        // when

        mockMvc.perform(get(RestaurantOrderController.RESTAURANT_ORDER_DETAILS, restaurantName, orderNumber))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name(RestaurantOrderController.RESTAURANT_ORDER_DETAILS_PAGE));

        // then
        verify(orderService).getOrderDetails(orderNumber);
    }

    @Test
    void ordersTest() throws Exception {
        // given
        String restaurantName = "Sushi Master";
        Restaurant restaurant = SomeFixtures.someRestaurant();
        RestaurantDTO restaurantDTO = SomeFixtures.someRestaurantDTO();
        // when then
        when(restaurantService.findRestaurantByNameWithOrders(restaurantName)).thenReturn(restaurant);
        when(restaurantMapper.map(restaurant)).thenReturn(restaurantDTO);
        mockMvc.perform(get(RestaurantOrderController.RESTAURANT_ORDERS, restaurantName))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("completeOrders"))
                .andExpect(model().attributeExists("activeOrders"))
                .andExpect(view().name(RestaurantOrderController.RESTAURANT_ORDERS_PAGE));

    }
}
