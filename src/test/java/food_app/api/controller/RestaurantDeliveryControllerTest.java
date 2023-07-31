package food_app.api.controller;

import Food_app.api.controller.restaurant.RestaurantDeliveryController;
import Food_app.api.dto.StreetDTO;
import Food_app.business.RestaurantService;
import Food_app.business.RestaurantStreetService;
import Food_app.domain.Restaurant;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RestaurantDeliveryController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantDeliveryControllerTest {
    @MockBean
    private final RestaurantStreetService restaurantStreetService;
    @MockBean
    private final RestaurantService restaurantService;

    private MockMvc mockMvc;
    @Test
    void deliveryTest() throws Exception {
        // given
        String name = "restaurant";
       when(restaurantService.findRestaurantByNameWithOrders(name)).thenReturn(SomeFixtures.someRestaurant());
        // when

        mockMvc.perform(get(RestaurantDeliveryController.RESTAURANT_DELIVERY, name))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("streets"))
                .andExpect(view().name(RestaurantDeliveryController.RESTAURANT_DELIVERY_PAGE));
    }

    @Test
    void addStreetFormTest() throws Exception {
        // given
        String name = "restaurant";
        when(restaurantService.findRestaurantByNameWithOrders(name)).thenReturn(SomeFixtures.someRestaurant());
        // when

        mockMvc.perform(get(RestaurantDeliveryController.RESTAURANT_ADD_STREET, name))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("streetDTO"))
                .andExpect(view().name(RestaurantDeliveryController.RESTAURANT_ADD_STREET_PAGE));
    }

    @Test
    void addStreetTest() throws Exception {
        // given
        String name = "restaurant";
        StreetDTO streetDTO=SomeFixtures.someStreetDto();
        Restaurant restaurant=SomeFixtures.someRestaurant();
        when(restaurantService.findRestaurantByNameWithMeals(name)).thenReturn(SomeFixtures.someRestaurant());
        doNothing().when(restaurantStreetService).createRestaurantStreet(streetDTO.getName(),restaurant);
        // when

        mockMvc.perform(post(RestaurantDeliveryController.RESTAURANT_ADD_STREET, name).flashAttr("streetDTO",streetDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(RestaurantDeliveryController.RESTAURANT_REDIRECT_RESTAURANT_DELIVERY.formatted(name)));
    }

    @Test
    void removeStreetTest() throws Exception {
        // given
        String name = "restaurant";
        String streetName = "streetName";
        Restaurant restaurant=SomeFixtures.someRestaurant();
        when(restaurantService.findRestaurantByNameWithMeals(name)).thenReturn(SomeFixtures.someRestaurant());
        doNothing().when(restaurantStreetService).removeStreetFromRestaurant(streetName,restaurant);
        // when

        mockMvc.perform(delete(RestaurantDeliveryController.RESTAURANT_REMOVE_STREET, name,streetName))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(RestaurantDeliveryController.RESTAURANT_REDIRECT_RESTAURANT_DELIVERY.formatted(name)));
    }


}
