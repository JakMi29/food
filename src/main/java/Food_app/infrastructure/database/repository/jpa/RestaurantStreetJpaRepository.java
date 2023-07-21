package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.entity.RestaurantStreetEntity;
import Food_app.infrastructure.database.entity.StreetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantStreetJpaRepository extends JpaRepository<RestaurantStreetEntity, Integer> {
    List<RestaurantStreetEntity> findByRestaurant(RestaurantEntity restaurant);

    void removeByStreetAndRestaurant(StreetEntity street, RestaurantEntity restaurant);

    List<RestaurantStreetEntity> findByStreet(StreetEntity street);
}
