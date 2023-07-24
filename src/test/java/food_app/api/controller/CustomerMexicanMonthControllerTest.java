package food_app.api.controller;

import Food_app.api.controller.customer.CustomerController;
import Food_app.api.controller.customer.CustomerMexicanMonthController;
import Food_app.api.dto.FoodApiMealDetailsDTO;
import Food_app.api.dto.mapper.FoodApiDetailsMapper;
import Food_app.business.FoodApiService;
import Food_app.domain.FoodApiMeal;
import Food_app.domain.FoodApiMealDetails;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = CustomerMexicanMonthController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerMexicanMonthControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private FoodApiService foodApiService;
    @MockBean
    private final FoodApiDetailsMapper foodApiDetailsMapper;
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
        mockMvc.perform(get(CustomerMexicanMonthController.CUSTOMER_MEXICAN_MONTH, name, pageNumber))

                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("pageNumber"))
                .andExpect(model().attributeExists("isLastPage"))
                .andExpect(model().attributeExists("foodApiMeals"))
                .andExpect(view().name(CustomerMexicanMonthController.CUSTOMER_MEXICAN_MONTH_PAGE));

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
        var result=mockMvc.perform(get(CustomerMexicanMonthController.CUSTOMER_MEXICAN_MONTH_RECEIPT, name, id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("foodApiMeal"))
                .andExpect(view().name(CustomerMexicanMonthController.CUSTOMER_MEXICAN_MONTH_RECEIPT_PAGE));
        verify(foodApiService).getFoodApiMealDetails(id);
        verify(foodApiDetailsMapper).map(foodApiMealDetails);
    }
}
