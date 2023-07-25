package food_app.bussines;

import Food_app.business.RestaurantService;
import Food_app.business.RestaurantStreetService;
import Food_app.business.dao.MealDAO;
import Food_app.business.dao.OrderDAO;
import Food_app.business.dao.RestaurantDAO;
import Food_app.business.dao.RestaurantOwnerDAO;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantOwner;
import Food_app.domain.exception.NotFoundException;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantDAO restaurantDAO;
    @Mock
    private RestaurantOwnerEntityMapper restaurantOwnerMapper;
    @Mock
    private RestaurantEntityMapper restaurantEntityMapper;
    @Mock
    private RestaurantOwnerDAO restaurantOwnerDAO;
    @Mock
    private RestaurantStreetService restaurantStreetService;
    @Mock
    private MealDAO mealDAO;
    @Mock
    private OrderDAO orderDAO;
    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void testFindRestaurantByName_ExistingName_ReturnsRestaurant() {
        String restaurantName = "Sample Restaurant";
        Restaurant expectedRestaurant = SomeFixtures.someRestaurant();

        when(restaurantDAO.findByName(restaurantName)).thenReturn(Optional.of(expectedRestaurant));

        Restaurant actualRestaurant = restaurantService.findRestaurantByName(restaurantName);

        assertNotNull(actualRestaurant);
        assertEquals(expectedRestaurant, actualRestaurant);
    }

    @Test
    public void testFindRestaurantByName_NonExistingName_ThrowsNotFoundException() {
        String restaurantName = "Non-Existing Restaurant";

        when(restaurantDAO.findByName(restaurantName)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.findRestaurantByName(restaurantName));
    }
    @Test
    public void testFindRestaurantByNameWithOrders_ExistingName_ReturnsRestaurantWithOrders() {
        String restaurantName = "Sample Restaurant";
        Restaurant expectedRestaurant = SomeFixtures.someRestaurant();


        when(restaurantDAO.findByNameWithOrders(restaurantName)).thenReturn(Optional.of(expectedRestaurant));

        Restaurant actualRestaurant = restaurantService.findRestaurantByNameWithOrders(restaurantName);

        assertNotNull(actualRestaurant);
        assertEquals(expectedRestaurant, actualRestaurant);
        assertFalse(actualRestaurant.getOrders().isEmpty());
    }

    @Test
    public void testFindRestaurantByNameWithOrders_NonExistingName_ThrowsNotFoundException() {
        String restaurantName = "Non-Existing Restaurant";

        when(restaurantDAO.findByNameWithOrders(restaurantName)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.findRestaurantByNameWithOrders(restaurantName));
    }

    @Test
    public void testFindRestaurantByRestaurantOwnerEmail_ExistingEmail_ReturnsRestaurant() {
        String ownerEmail = "owner@example.com";
        Integer ownerId = 1;
        RestaurantOwner owner = SomeFixtures.someRestaurantOwner();
        Restaurant expectedRestaurant = SomeFixtures.someRestaurant();
        when(restaurantOwnerDAO.findByEmail(ownerEmail)).thenReturn(Optional.of(owner));

        when(restaurantDAO.findById(ownerId)).thenReturn(Optional.of(expectedRestaurant));

        Restaurant actualRestaurant = restaurantService.findRestaurantByRestaurantOwnerEmail(ownerEmail);

        assertNotNull(actualRestaurant);
        assertEquals(expectedRestaurant, actualRestaurant);
    }

    @Test
    public void testFindRestaurantByRestaurantOwnerEmail_NonExistingEmail_ThrowsNotFoundException() {
        String ownerEmail = "nonexisting@example.com";

        when(restaurantOwnerDAO.findByEmail(ownerEmail)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.findRestaurantByRestaurantOwnerEmail(ownerEmail));
    }

    @Test
    public void testFindRestaurantByRestaurantOwnerEmail_ExistingEmail_NoRestaurant_ThrowsNotFoundException() {
        String ownerEmail = "owner@example.com";
        Integer ownerId = 1;
        RestaurantOwner owner = SomeFixtures.someRestaurantOwner();

        when(restaurantOwnerDAO.findByEmail(ownerEmail)).thenReturn(Optional.of(owner));

        when(restaurantDAO.findById(ownerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.findRestaurantByRestaurantOwnerEmail(ownerEmail));
    }

}
