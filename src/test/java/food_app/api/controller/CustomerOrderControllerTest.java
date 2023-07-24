package food_app.api.controller;

import Food_app.api.controller.customer.CustomerOrderController;
import Food_app.api.dto.OrderDetailsDTO;
import Food_app.api.dto.mapper.CustomerMapper;
import Food_app.business.*;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerOrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerOrderControllerTest {
    @MockBean
    private final OrderService orderService;
    @MockBean
    private final CustomerService customerService;
    @MockBean
    private final CustomerMapper customerMapper;
    private CustomerOrderController customerOrderController;
    private MockMvc mockMvc;
    @Test
    void testCancelOrder() throws Exception {
        // given
        String name = "John";
        String orderNumber = "12345";

        // when
        mockMvc.perform(delete(CustomerOrderController.CANCEL_ORDER, name, orderNumber))
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
        mockMvc.perform(get(CustomerOrderController.CUSTOMER_ORDER_DETAILS, name, orderNumber))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(model().attributeExists("name"))
                .andExpect(view().name(CustomerOrderController.CUSTOMER_ORDER_DETAILS_PAGE))
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
        mockMvc.perform(post(CustomerOrderController.CREATE_ORDER, name, restaurantName).params(parameters))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customer/" + name + "/restaurants/0/6"));

    }

    @Test
    void testMapMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("mealQuantities[meal1]", "quantity1");
        inputMap.put("mealQuantities[meal2]", "quantity2");


        Method privateMethod = CustomerOrderController.class.getDeclaredMethod("mapMap", Map.class);
        privateMethod.setAccessible(true);

        // when
        Map<String, String> result = (Map<String, String>) privateMethod.invoke(customerOrderController, inputMap);

        // then
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("meal1", "quantity1");
        expectedMap.put("meal2", "quantity2");

        Assertions.assertEquals(expectedMap, result);
    }
}
