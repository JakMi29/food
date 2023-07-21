package food_app.api.controller;

import Food_app.api.controller.AuthController;
import Food_app.api.controller.CustomerController;
import Food_app.api.dto.CreateCustomerDTO;
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
import org.testcontainers.shaded.org.bouncycastle.cert.crmf.AuthenticatorControl;

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

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationControllerTest {
    @MockBean
    private final CustomerService customerService;
    @MockBean
    private final RestaurantOwnerService restaurantOwnerService;
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource
    void thatPhoneValidationWorksCorrectly(Boolean correctPhone, String phone) throws Exception {
        // given
        CreateCustomerDTO createCustomerDTO=SomeFixtures.someCreateCustomerDTO();
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        Map<String, String> createCustomerMap = new HashMap<>();
        createCustomerMap.put("name", createCustomerDTO.getName());
        createCustomerMap.put("surname", createCustomerDTO.getSurname());
        createCustomerMap.put("userName", createCustomerDTO.getUserName());
        createCustomerMap.put("password", createCustomerDTO.getPassword());
        createCustomerMap.put("email", createCustomerDTO.getEmail());
        createCustomerMap.put("country", createCustomerDTO.getCountry());
        createCustomerMap.put("city", createCustomerDTO.getCity());
        createCustomerMap.put("postalCode", createCustomerDTO.getPostalCode());
        createCustomerMap.put("street", createCustomerDTO.getStreet());
        createCustomerMap.put("homeNumber", createCustomerDTO.getHomeNumber());
        createCustomerMap.put("phone", phone);
        parameters.setAll(createCustomerMap);
        System.out.println(parameters);

        // when, then
        if (correctPhone) {
            doNothing().when(customerService).createCustomer(any());
            mockMvc.perform(post(AuthController.REGISTRATION_CUSTOMER).params(parameters))
                    .andExpect(status().is3xxRedirection());

        } else {
            mockMvc.perform(post(AuthController.REGISTRATION_CUSTOMER).params(parameters))
                    .andExpect(status().isBadRequest());
        }
    }

    public static Stream<Arguments> thatPhoneValidationWorksCorrectly() {
        return Stream.of(
              Arguments.of(false, ""),
                Arguments.of(false, "+48 504 203 260@@"),
                Arguments.of(false, "+48.504.203.260"),
                Arguments.of(false, "+55(123) 456-78-90-"),
                Arguments.of(false, "+55(123) - 456-78-90"),
                Arguments.of(false, "504.203.260"),
                Arguments.of(false, " "),
                Arguments.of(false, "-"),
                Arguments.of(false, "()"),
                Arguments.of(false, "() + ()"),
                Arguments.of(false, "(21 7777"),
                Arguments.of(false, "+48 (21)"),
                Arguments.of(false, "+"),
                Arguments.of(false, " 1"),
                Arguments.of(false, "1"),
                Arguments.of(false, "+48 (12) 504 203 260"),
                Arguments.of(false, "+48 (12) 504-203-260"),
                Arguments.of(false, "+48(12)504203260"),
                Arguments.of(false, "555-5555-555"),
                Arguments.of(true, "+48 504 203 260")
        );
    }

}
