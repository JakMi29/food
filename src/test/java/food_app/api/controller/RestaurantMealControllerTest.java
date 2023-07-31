package food_app.api.controller;

import Food_app.api.controller.restaurant.RestaurantMealController;
import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.mapper.RestaurantMapper;
import Food_app.business.MealService;
import Food_app.business.RestaurantService;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RestaurantMealController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantMealControllerTest {
    @MockBean
    private final MealService mealService;
    @MockBean
    private final RestaurantService restaurantService;
    @MockBean
    private final RestaurantMapper restaurantMapper;

    private MockMvc mockMvc;
    @Test
    void deleteMealTest() throws Exception {
        // given
        String name = "restaurant";
        String mealName = "mealName";
       doNothing().when(mealService).deleteByNameAndRestaurantName(mealName,name);
        // when

        mockMvc.perform(delete(RestaurantMealController.RESTAURANT_DELETE_MEAL, name, mealName))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(RestaurantMealController.RESTAURANT_REDIRECT_MENU.formatted(name)));

        // then
        verify(mealService).deleteByNameAndRestaurantName(mealName, name);
    }

    @Test
    void createMealFormTest() throws Exception {
        // given
        String name = "Sushi Master";

        // when then
        mockMvc.perform(get(RestaurantMealController.RESTAURANT_CREATE_MEAL, name))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mealDTO"))
                .andExpect(model().attributeExists("name"))
                .andExpect(view().name(RestaurantMealController.RESTAURANT_DELETE_MEAL_PAGE));
    }
    @Test
    void makeMealTest() throws Exception {
        // given
        String name = "Sushi Master";
        CreateMealDTO mealDTO=SomeFixtures.someCreateMealDTO();

        // when then
        mockMvc.perform(post(RestaurantMealController.RESTAURANT_CREATE_MEAL, name).flashAttr("mealDTO",mealDTO))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mealDTO"))
                .andExpect(model().attributeExists("name"))
                .andExpect(view().name(RestaurantMealController.RESTAURANT_MEAL_DONE_PAGE));
    }

    @Test
    void updateMealFormTest() throws Exception {
        // given
        String name = "Sushi Master";
        String mealName = "meal name";

        // when then
        mockMvc.perform(get(RestaurantMealController.RESTAURANT_UPDATE_MEAL, name,mealName))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mealDTO"))
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("meaName"))
                .andExpect(view().name(RestaurantMealController.RESTAURANT_UPDATE_MEAL_PAGE));
    }
    @Test
    void updateMealTest() throws Exception {
        // given
        String name = "Sushi Master";
        String mealName = "mealName";
        CreateMealDTO mealDTO=SomeFixtures.someCreateMealDTO();

        doNothing().when(mealService).updateMeal(name,mealName,mealDTO);
        when(restaurantService.findRestaurantByNameWithMeals(any())).thenReturn(SomeFixtures.someRestaurant());
        // when then
        mockMvc.perform(put(RestaurantMealController.RESTAURANT_UPDATE_MEAL, name,mealName).flashAttr("mealDTO",mealDTO))
                .andExpect(status().is3xxRedirection())
                //.andExpect(model().attributeExists("meals"))
                .andExpect(view().name(RestaurantMealController.RESTAURANT_REDIRECT_MENU_PAGE.formatted(name)));
    }
}
