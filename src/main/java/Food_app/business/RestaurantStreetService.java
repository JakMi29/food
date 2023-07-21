package Food_app.business;

import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantStreet;
import Food_app.domain.Street;
import Food_app.infrastructure.database.repository.RestaurantStreetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor

public class RestaurantStreetService {

    private final RestaurantStreetRepository restaurantStreetRepository;
    private final StreetService streetService;


    @Transactional
    public void createRestaurantStreet(String streetName, Restaurant restaurant) {
        List<RestaurantStreet> streets = findRestaurantStreetsByRestaurant(restaurant).stream()
                .filter(c -> c.getStreet().getName().equals(streetName)).toList();
        if (!streets.isEmpty())
            throw new RuntimeException("this street is currently in the list of served streets!");
        Street street = streetService.findOrCreateStreet(streetName);
        restaurantStreetRepository.createRestaurantStreet(street, restaurant);
    }

    @Transactional
    public List<RestaurantStreet> findRestaurantStreetsByStreetName(String streetName) {
        Street street = streetService.findStreet(streetName).orElseThrow();
        return restaurantStreetRepository.findAllByStreet(street);
    }

    @Transactional
    public List<RestaurantStreet> findRestaurantStreetsByRestaurant(Restaurant restaurant) {
        return restaurantStreetRepository.findRestaurantStreetsByRestaurant(restaurant);
    }

    @Transactional
    public void removeStreetFromRestaurant(String streetName, Restaurant restaurant) {
        Street street = streetService.findStreet(streetName).orElseThrow();
        restaurantStreetRepository.removeStreetFromRestaurant(street, restaurant);
    }
}
