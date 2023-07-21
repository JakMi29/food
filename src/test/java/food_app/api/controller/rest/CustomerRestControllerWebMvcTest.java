package food_app.api.controller.rest;

import Food_app.api.controller.rest.CustomerRestController;
import Food_app.api.dto.RestaurantDTO;
import Food_app.api.dto.mapper.CustomerOrderMapper;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.api.dto.mapper.RestaurantMenuMapper;
import Food_app.business.CustomerService;
import Food_app.business.OrderService;
import Food_app.business.RestaurantPaginationService;
import Food_app.business.RestaurantService;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import food_app.util.SomeFixtures;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerRestControllerWebMvcTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private RestaurantPaginationService restaurantPaginationService;
    @MockBean
    private RestaurantEntityMapper restaurantEntityMapper;
    @MockBean
    private RestaurantMapper restaurantMapper;
    @MockBean
    private OrderService orderService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private RestaurantMenuMapper restaurantMenuMapper;
    @MockBean
    private CustomerOrderMapper customerOrderMapper;



}
