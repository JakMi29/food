package Food_app.business;

import Food_app.business.dao.MealDAO;
import Food_app.business.dao.OrderDAO;
import Food_app.business.dao.RestaurantDAO;
import Food_app.business.dao.RestaurantOwnerDAO;
import Food_app.domain.Meal;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantOwner;
import Food_app.domain.RestaurantStreet;
import Food_app.domain.exception.NotFoundException;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RestaurantService {
    private final RestaurantDAO restaurantDAO;
    private final RestaurantOwnerEntityMapper restaurantOwnerMapper;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final RestaurantOwnerDAO restaurantOwnerDAO;
    private final RestaurantStreetService restaurantStreetService;
    private final MealDAO mealDAO;
    private final OrderDAO orderDAO;

    @Transactional
    Page<RestaurantEntity> findAll(Pageable pageable) {
        return restaurantDAO.findAll(pageable);
    }


    @Transactional
    public Restaurant findRestaurantByRestaurantOwnerEmail(String email) {
        RestaurantOwner owner = restaurantOwnerDAO.findByEmail(email).orElseThrow();
        Optional<Restaurant> restaurant = restaurantDAO.findById(owner.getRestaurantOwnerId());
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by restaurant owner email: [%s]".formatted(email));
        }
        return restaurant.get();
    }

    @Transactional
    public Restaurant findRestaurantByNameWithMeals(String name) {
        Optional<Restaurant> restaurant = restaurantDAO.findByNameWithMeals(name);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by restaurant name: %s".formatted(name));
        }
        Set<Meal> meals = new HashSet<>(mealDAO.findByRestaurant(restaurant.get()));
        return restaurant.get().withMeals(meals);
    }

    @Transactional
    public Restaurant findRestaurantByNameWithOrders(String name) {
        Optional<Restaurant> restaurant = restaurantDAO.findByNameWithOrders(name);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by restaurant name: %s".formatted(name));
        }
        return restaurant.get();
    }

    @Transactional
    public Restaurant findRestaurantByName(String name) {
        Optional<Restaurant> restaurant = restaurantDAO.findByName(name);
        if (restaurant.isEmpty()) {
            throw new NotFoundException("Could not find restaurant by restaurant name: %s".formatted(name));
        }
        return restaurant.get();
    }


    public void createRestaurant(Restaurant restaurant) {
        restaurantDAO.saveRestaurant(restaurantEntityMapper.map(restaurant));
    }

    public List<Restaurant> findRestaurantsByDeliveryStreet(String streetName) {
        List<RestaurantStreet> restaurants = restaurantStreetService.findRestaurantStreetsByStreetName(streetName);
        return restaurants.stream().map(RestaurantStreet::getRestaurant).toList();
    }
}
