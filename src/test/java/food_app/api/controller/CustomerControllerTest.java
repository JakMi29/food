package food_app.api.controller;

import Food_app.api.controller.customer.CustomerController;
import Food_app.api.dto.mapper.CustomerMapper;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.business.*;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.security.UserRepository;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerControllerTest {

    @MockBean
    @SuppressWarnings("unused")
    private final UserRepository userRepository;
    @MockBean
    private final RestaurantEntityMapper restaurantEntityMapper;
    @MockBean
    private final RestaurantService restaurantService;
    @MockBean
    private final RestaurantMapper restaurantMapper;
    @MockBean
    private final RestaurantMenuMapper restaurantMenuMapper;
    @MockBean
    private final OrderService orderService;
    @MockBean
    private final CustomerService customerService;
    @MockBean
    private final CustomerMapper customerMapper;
    @MockBean
    private final RestaurantPaginationService restaurantPaginationService;

    private CustomerController customerController;
    private MockMvc mockMvc;
    @MockBean
    private FoodApiService foodApiService;



    @Test
    void testRestaurants() throws Exception {
        // Given
        String name = "John";
        Integer pageNumber = 1;
        Integer pageSize = 5;
        List<RestaurantEntity> restaurants = List.of(SomeFixtures.someRestaurantEntity());

        Page<RestaurantEntity> mockPage = mock(Page.class);

        when(restaurantPaginationService.paginate(eq(pageNumber), eq(5))).thenReturn(mockPage);
        when(mockPage.getContent()).thenReturn(restaurants);
        when(mockPage.isLast()).thenReturn(false);

        mockMvc.perform(get(CustomerController.CUSTOMER_RESTAURANTS, name, pageNumber,pageSize))
                .andExpect(status().isOk())
                .andExpect(view().name(CustomerController.CUSTOMER_RESTAURANTS_PAGE))
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("pageNumber"))
                .andExpect(model().attributeExists("isLastPage"))
                .andExpect(model().attributeExists("restaurants"))
                .andReturn();

        verify(restaurantPaginationService).paginate(pageNumber, 5);
    }
    @Test
    void restaurantsByStreetTest() throws Exception {
        // Given
        String name = "name";
        String streetName="streetName";

        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String, String> mealMap = new HashMap<>();
        mealMap.put("streetName",streetName);
        parameters.setAll(mealMap);

        when(restaurantService.findRestaurantByNameWithMeals(name)).thenReturn(SomeFixtures.someRestaurant());

        mockMvc.perform(get(CustomerController.CUSTOMER_RESTAURANTS_BY_STREET, name).params(parameters))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("restaurants"))
                .andExpect(view().name(CustomerController.CUSTOMER_RESTAURANTS_BY_STREET_PAGE))
                .andReturn();

    }

    @Test
    void restaurantMenuTest() throws Exception {
        // Given
        String name = "name";
        String restaurantName="restaurantName";

        when(restaurantMenuMapper.map(any())).thenReturn(SomeFixtures.someRestaurantMenu());

        mockMvc.perform(get(CustomerController.CUSTOMER_RESTAURANT_MENU, name, restaurantName))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("restaurantName"))
                .andExpect(model().attributeExists("restaurant"))
                .andExpect(view().name(CustomerController.CUSTOMER_RESTAURANT_MENU_PAGE))
                .andReturn();

    }


}
