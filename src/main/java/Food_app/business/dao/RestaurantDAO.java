package Food_app.business.dao;

import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {
    Optional<Restaurant> findById(Integer id);

    Page<RestaurantEntity> findAll(Pageable pageable);

    List<Restaurant> findAll();

    Optional<Restaurant> findByNameWithOrders(String name);

    Optional<Restaurant> findByNameWithMeals(String name);

    void createRestaurant(Restaurant restaurant);

    void saveRestaurant(RestaurantEntity map);

    Optional<Restaurant> findByName(String name);
}
