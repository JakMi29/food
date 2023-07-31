package food_app.bussines;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.mapper.CreateMealMapper;
import Food_app.business.MealService;
import Food_app.business.OrderMealService;
import Food_app.business.RestaurantService;
import Food_app.business.dao.MealDAO;
import Food_app.domain.Meal;
import Food_app.domain.Restaurant;
import Food_app.domain.exception.MealAlreadyExist;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

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
    @Mock
    private Resource resource;

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
    @Test
    public void testAddMealNewMealAddsSuccessfully() throws IOException {
        String restaurantName = "Sample Restaurant";
        CreateMealDTO mealDto = SomeFixtures.someCreateMealDTO();

        Restaurant existingRestaurant = SomeFixtures.someRestaurant();
        when(restaurantService.findRestaurantByNameWithMeals(restaurantName)).thenReturn(existingRestaurant);

        Meal mappedMeal = SomeFixtures.someMeal().withName("name");
        when(mealMapper.map(any())).thenReturn(mappedMeal);

        doNothing().when(mealDAO).createMeal(any());
        mealService.addMeal(mealDto, restaurantName);
    }
    @Test
    public void testAddMealNewMealAddsThrowException() throws IOException {
        String restaurantName = "Sample Restaurant";
        CreateMealDTO mealDto = SomeFixtures.someCreateMealDTO();

        Restaurant existingRestaurant = SomeFixtures.someRestaurant();
        when(restaurantService.findRestaurantByNameWithMeals(restaurantName)).thenReturn(existingRestaurant);

        Meal mappedExistingMeal = SomeFixtures.someMeal().withName("bigos");
        when(mealMapper.map(any())).thenReturn(mappedExistingMeal);

        Assertions.assertThrows(MealAlreadyExist.class, () ->  mealService.addMeal(mealDto, restaurantName));

    }
    @Test
    public void testDeleteOldPhoto_FileExists_FileDeleted() throws IOException {
        String path = "/example.jpg";
        File mockFile = mock(File.class);

        when(resourceLoader.getResource("classpath:static" + path)).thenReturn(resource);
        when(resource.getFile()).thenReturn(mockFile);
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.delete()).thenReturn(true);

        mealService.deleteOldPhoto(path);

        verify(resourceLoader).getResource("classpath:static" + path);
        verify(resource).getFile();
        verify(mockFile).exists();
        verify(mockFile).delete();
    }

    @Test
    public void testDeleteOldPhoto_FileDoesNotExist_LogsInfo() throws IOException {
        String path = "/non_existent.jpg";
        File mockFile = mock(File.class);

        when(resourceLoader.getResource("classpath:static" + path)).thenReturn(resource);
        when(resource.getFile()).thenReturn(mockFile);
        when(mockFile.exists()).thenReturn(false);

        mealService.deleteOldPhoto(path);

        verify(resourceLoader).getResource("classpath:static" + path);
        verify(resource).getFile();
        verify(mockFile).exists();
        verify(mockFile, never()).delete();
    }


}

