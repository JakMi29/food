package food_app.api.controller;

import Food_app.api.controller.CustomerController;
import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.api.dto.OrderDetailsDTO;
import Food_app.api.dto.mapper.CustomerMapper;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.business.*;
import Food_app.domain.FoodApiMeal;
import Food_app.domain.FoodApiMealDetails;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.security.UserRepository;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerControllerTest {

    @MockBean
    @SuppressWarnings("unused")
    private final UserRepository userRepository;
    @MockBean
    @SuppressWarnings("unused")
    private final RestaurantEntityMapper restaurantEntityMapper;
    @MockBean
    @SuppressWarnings("unused")
    private final RestaurantService restaurantService;
    @MockBean
    @SuppressWarnings("unused")
    private final RestaurantMapper restaurantMapper;
    @MockBean
    @SuppressWarnings("unused")
    private final RestaurantMenuMapper restaurantMenuMapper;
    @MockBean
    @SuppressWarnings("unused")
    private final OrderService orderService;
    @MockBean
    @SuppressWarnings("unused")
    private final CustomerService customerService;
    @MockBean
    @SuppressWarnings("unused")
    private final CustomerMapper customerMapper;
    @MockBean
    @SuppressWarnings("unused")
    private final RestaurantPaginationService restaurantPaginationService;
    @MockBean
    @SuppressWarnings("unused")
    private final FoodApiDetailsMapper foodApiDetailsMapper;
    private CustomerController customerController;
    private MockMvc mockMvc;
    @MockBean
    @SuppressWarnings("unused")
    private FoodApiService foodApiService;


    @Test
    void testMexicanMonth() throws Exception {
        //given
        String name = "John";
        int pageNumber = 1;
        List<FoodApiMeal> meals = Arrays.asList(
                new FoodApiMeal(),
                new FoodApiMeal(),
                new FoodApiMeal(),
                new FoodApiMeal(),
                new FoodApiMeal()
        );

        when(foodApiService.getFoodMealPage(pageNumber)).thenReturn(meals);

        //when
        mockMvc.perform(get(CustomerController.CUSTOMER_MEXICAN_MONTH, name, pageNumber))

                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("pageNumber"))
                .andExpect(model().attributeExists("isLastPage"))
                .andExpect(model().attributeExists("foodApiMeals"))
                .andExpect(view().name(CustomerController.CUSTOMER_MEXICAN_MONTH_PAGE));

        verify(foodApiService).getFoodMealPage(pageNumber);
    }

    @Test
    void testMexicanMonthReceipt() throws Exception {
        // given
        String name = "John";
        Integer id = 123;
        FoodApiMealDetails foodApiMealDetails = new FoodApiMealDetails();
        FoodApiMealDetailsDTO mealDetailsDTO = new FoodApiMealDetailsDTO();

        when(foodApiService.getFoodApiMealDetails(id)).thenReturn(foodApiMealDetails);
        when(foodApiDetailsMapper.map(foodApiMealDetails)).thenReturn(mealDetailsDTO);

        // when
        var result=mockMvc.perform(get(CustomerController.CUSTOMER_MEXICAN_MONTH_RECEIPT, name, id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("foodApiMeal"))
                .andExpect(view().name(CustomerController.CUSTOMER_MEXICAN_MONTH_RECEIPT_PAGE));
        verify(foodApiService).getFoodApiMealDetails(id);
        verify(foodApiDetailsMapper).map(foodApiMealDetails);
    }

    @Test
    void testRestaurants() throws Exception {
        // Given
        String name = "John";
        Integer pageNumber = 1;
        List<RestaurantEntity> restaurants = List.of(SomeFixtures.someRestaurantEntity());

        Page<RestaurantEntity> mockPage = mock(Page.class);

        when(restaurantPaginationService.paginate(eq(pageNumber), eq(5))).thenReturn(mockPage);
        when(mockPage.getContent()).thenReturn(restaurants);
        when(mockPage.isLast()).thenReturn(false);

        mockMvc.perform(get(CustomerController.CUSTOMER_RESTAURANTS, name, pageNumber))
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
    void testCancelOrder() throws Exception {
        // given
        String name = "John";
        String orderNumber = "12345";

        // when
        mockMvc.perform(delete(CustomerController.CANCEL_ORDER, name, orderNumber))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/" + name + "/orders"))
                .andExpect(redirectedUrl("/customer/" + name + "/orders"));

        // then
        verify(orderService).cancelOrder(orderNumber);
    }

    @Test
    void testOrderDetails() throws Exception {
        // given
        String name = "John";
        String orderNumber = "123456";
        OrderDetailsDTO orderDetailsDTO = SomeFixtures.someOrderDetailsDto();
        when(orderService.getOrderDetails(orderNumber)).thenReturn(orderDetailsDTO);


        // when
        mockMvc.perform(get(CustomerController.CUSTOMER_ORDER_DETAILS, name, orderNumber))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attributeExists("name"))
                .andExpect(view().name(CustomerController.CUSTOMER_ORDER_DETAILS_PAGE))
                .andReturn();

        // then
        verify(orderService).getOrderDetails(orderNumber);
    }


    @Test
    void testCreateOrder() throws Exception {
        // given
        String name = "John";
        String restaurantName = "Restaurant";
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String, String> mealMap = new HashMap<>();
        mealMap.put("meal1", "quantity1");
        mealMap.put("meal2", "quantity2");
        parameters.setAll(mealMap);

        // when//then
        mockMvc.perform(post(CustomerController.CREATE_ORDER, name, restaurantName).params(parameters))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customer/" + name + "/restaurants/0"));

    }

    @Test
    void testMapMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("mealQuantities[meal1]", "quantity1");
        inputMap.put("mealQuantities[meal2]", "quantity2");


        Method privateMethod = CustomerController.class.getDeclaredMethod("mapMap", Map.class);
        privateMethod.setAccessible(true);

        // when
        Map<String, String> result = (Map<String, String>) privateMethod.invoke(customerController, inputMap);

        // then
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("meal1", "quantity1");
        expectedMap.put("meal2", "quantity2");

        Assertions.assertEquals(expectedMap, result);
    }



}
