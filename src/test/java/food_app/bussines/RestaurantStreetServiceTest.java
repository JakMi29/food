package food_app.bussines;

import Food_app.business.RestaurantStreetService;
import Food_app.business.StreetService;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantStreet;
import Food_app.domain.Street;
import Food_app.infrastructure.database.repository.RestaurantStreetRepository;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantStreetServiceTest {

    @InjectMocks
    private RestaurantStreetService restaurantStreetService;

    @Mock
    private RestaurantStreetRepository restaurantStreetRepository;
    @Mock
    private StreetService streetService;


    @Test
    public void testFindRestaurantStreetsByRestaurant() {
        Restaurant restaurant=SomeFixtures.someRestaurant();

        when(restaurantStreetRepository.findRestaurantStreetsByRestaurant(restaurant))
                .thenReturn(List.of(SomeFixtures.someRestaurantStreet()));

        List<RestaurantStreet> streets=restaurantStreetService.findRestaurantStreetsByRestaurant(restaurant);
        Assertions.assertFalse(streets.isEmpty());
    }

    @Test
    public void testRemoveStreetFromRestaurant() {
        Restaurant restaurant=SomeFixtures.someRestaurant();
        String streetName="streetName";
        Street street=SomeFixtures.someStreet();

        when(streetService.findStreet(streetName))
                .thenReturn(Optional.of(street))
                .thenReturn(Optional.empty());

        restaurantStreetService.removeStreetFromRestaurant(streetName,restaurant);
        Assertions.assertThrows(
                RuntimeException.class
                ,()->restaurantStreetService.removeStreetFromRestaurant(streetName,restaurant));
        verify(restaurantStreetRepository).removeStreetFromRestaurant(street,restaurant);

    }
    @Test
    public void testFindRestaurantStreetsByStreetName() {
        Restaurant restaurant=SomeFixtures.someRestaurant();
        String streetName="streetName";
        Street street=SomeFixtures.someStreet();

        when(streetService.findStreet(streetName))
                .thenReturn(Optional.of(street))
                .thenReturn(Optional.empty());

        restaurantStreetService.findRestaurantStreetsByStreetName(streetName);
        Assertions.assertThrows(
                RuntimeException.class
                ,()->restaurantStreetService.findRestaurantStreetsByStreetName(streetName));
        verify(restaurantStreetRepository).findAllByStreet(street);

    }
    @Test
    public void testCreateRestaurantStreet() {
        Restaurant restaurant=SomeFixtures.someRestaurant();
        String streetName="name";
        Street street=SomeFixtures.someStreet();
        RestaurantStreet restaurantStreet=SomeFixtures.someRestaurantStreet();

        when(restaurantStreetService.findRestaurantStreetsByRestaurant(restaurant))
                .thenReturn(List.of(restaurantStreet))
                .thenReturn(List.of());

        when(streetService.findOrCreateStreet(streetName))
                .thenReturn(street);

        Assertions.assertThrows(
                RuntimeException.class,
                ()->restaurantStreetService.createRestaurantStreet(streetName,restaurant));
        restaurantStreetService.createRestaurantStreet(streetName,restaurant);


        verify(restaurantStreetRepository).createRestaurantStreet(street,restaurant);

    }
}
