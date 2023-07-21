package Food_app.infrastructure.database.repository;

import Food_app.business.dao.RestaurantDAO;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    public Page<RestaurantEntity> findAll(Pageable pageable) {
        return restaurantJpaRepository.findAll(pageable);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantJpaRepository.findAll().stream().map(restaurantEntityMapper::map).toList();
    }

    @Override
    public Optional<Restaurant> findById(Integer id) {
        return restaurantJpaRepository.findById(id)
                .map(restaurantEntityMapper::map);
    }

    @Override
    public Optional<Restaurant> findByNameWithMeals(String name) {
        Optional<Restaurant> opt = restaurantJpaRepository.findWithMealsByName(name)
                .map(restaurantEntityMapper::mapWithMeals);
        return opt;
    }

    @Override
    public Optional<Restaurant> findByNameWithOrders(String name) {
        return restaurantJpaRepository.findWithOrdersByName(name)
                .map(restaurantEntityMapper::mapWithOrders);
    }

    public void createRestaurant(Restaurant restaurant) {
        restaurantJpaRepository.save(restaurantEntityMapper.map(restaurant));
    }

    @Override
    public void saveRestaurant(RestaurantEntity restaurantEntity) {
        restaurantJpaRepository.save(restaurantEntity);
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        return restaurantJpaRepository.findByName(name).map(restaurantEntityMapper::map);
    }

}
