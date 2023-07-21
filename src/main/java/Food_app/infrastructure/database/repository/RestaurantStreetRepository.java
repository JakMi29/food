package Food_app.infrastructure.database.repository;

import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantStreet;
import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.RestaurantStreetEntity;
import Food_app.infrastructure.database.repository.jpa.RestaurantStreetJpaRepository;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantStreetEntityMapper;
import Food_app.infrastructure.database.repository.mapper.StreetEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantStreetRepository {
    private final RestaurantStreetJpaRepository restaurantStreetJpaRepository;
    private final RestaurantStreetEntityMapper restaurantStreetEntityMapper;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final StreetEntityMapper streetEntityMapper;


    public List<RestaurantStreet> findRestaurantStreetsByRestaurant(Restaurant restaurant) {
        return restaurantStreetJpaRepository.findByRestaurant(restaurantEntityMapper.map(restaurant)).stream()
                .map(restaurantStreetEntityMapper::mapWithRestaurant).toList();
    }

    public void removeStreetFromRestaurant(Street street, Restaurant restaurant) {
        restaurantStreetJpaRepository
                .removeByStreetAndRestaurant(streetEntityMapper.map(street), restaurantEntityMapper.map(restaurant));
    }

    public List<RestaurantStreet> findAllByStreet(Street street) {
        return restaurantStreetJpaRepository
                .findByStreet(streetEntityMapper.map(street)).stream()
                .map(restaurantStreetEntityMapper::mapWithRestaurant)
                .toList();
    }


    public void createRestaurantStreet(Street street, Restaurant restaurant) {
        RestaurantStreetEntity restaurantStreetEntity = RestaurantStreetEntity.builder()
                .street(streetEntityMapper.map(street))
                .restaurant(restaurantEntityMapper.map(restaurant))
                .build();
        restaurantStreetJpaRepository.save(restaurantStreetEntity);
    }
}
