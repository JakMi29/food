package food_app.bussines;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.mapper.CreateMealMapper;
import Food_app.business.MealService;
import Food_app.business.OrderMealService;
import Food_app.business.PhotoNumberGenerator;
import Food_app.business.RestaurantService;
import Food_app.business.dao.MealDAO;
import Food_app.domain.Meal;
import Food_app.domain.Restaurant;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MealServiceTest {
    @InjectMocks
    private MealService mealService;
    @Mock
    private MealDAO mealDAO;

    @Mock
    private CreateMealMapper mealMapper;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private OrderMealService orderMealService;

    @Mock
    private ResourceLoader resourceLoader;

    @Test
    void testDeleteByNameAndRestaurantName() {
        // given
        String mealName = "Meal";
        String restaurantName = "Restaurant";
        Restaurant restaurant = SomeFixtures.someRestaurant();
        Meal mealToDelete = SomeFixtures.someMeal();

        when(restaurantService.findRestaurantByNameWithMeals(restaurantName)).thenReturn(restaurant);
        when(mealDAO.findByNameAndRestaurant(mealName, restaurant)).thenReturn(mealToDelete);
        // when
        mealService.deleteByNameAndRestaurantName(mealName, restaurantName);
        // then
        verify(orderMealService).deleteAllByMeal(mealToDelete);
        verify(mealDAO).deleteById(mealToDelete.getMealId());
    }

    @Test
    void testFindByNameAndRestaurantName() {
        // given
        String name = "Meal";
        String restaurantName = "Restaurant";
        Restaurant restaurant = SomeFixtures.someRestaurant();
        Meal expectedMeal = SomeFixtures.someMeal();
        when(restaurantService.findRestaurantByNameWithMeals(restaurantName)).thenReturn(restaurant);
        when(mealDAO.findByNameAndRestaurant(name, restaurant)).thenReturn(expectedMeal);

        // when
        Meal result = mealService.findByNameAndRestaurantName(name, restaurantName);

        // then
        assertEquals(expectedMeal, result);
    }
}

