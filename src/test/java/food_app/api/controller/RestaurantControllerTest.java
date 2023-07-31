package food_app.api.controller;

import Food_app.api.controller.restaurant.RestaurantController;
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

@WebMvcTest(controllers = RestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantControllerTest {

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
    void testMealsPage() throws Exception {
        // given
        String restaurantName = "Sushi Master";
        Restaurant restaurant = SomeFixtures.someRestaurant();
        RestaurantDTO restaurantDTO = SomeFixtures.someRestaurantDTO();

        when(restaurantService.findRestaurantByNameWithMeals(restaurantName)).thenReturn(restaurant);
        when(restaurantMapper.map(restaurant)).thenReturn(restaurantDTO);
        // when

        mockMvc.perform(get(RestaurantController.RESTAURANT_MENU, restaurantName))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("meals"))
                .andExpect(view().name(RestaurantController.RESTAURANT_MENU_PAGE));

        // then
        verify(restaurantService).findRestaurantByNameWithMeals(restaurantName);
    }

    /*@Test
    void deliveryTest() throws Exception {
        // given
        String restaurantName = "Sushi Master";
        List<RestaurantStreet> streets = List.of(SomeFixtures.someRestaurantStreet());
        Restaurant restaurant = SomeFixtures.someRestaurant();
        // when then
        when(restaurantService.findRestaurantByNameWithOrders(restaurantName)).thenReturn(restaurant);
        when(restaurantStreetService.findRestaurantStreetsByRestaurant(restaurant)).thenReturn(streets);

        mockMvc.perform(get(RestaurantDeliveryController.RESTAURANT_DELIVERY, restaurantName))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("streets"))
                .andExpect(view().name(RestaurantDeliveryController.RESTAURANT_DELIVERY_PAGE));
    }*/


}
