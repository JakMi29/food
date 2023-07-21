package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.MealEntity;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealJpaRepository extends JpaRepository<MealEntity, Integer> {

    @Query("SELECT e FROM MealEntity e WHERE e.name = :name AND e.restaurant = :restaurant")
    MealEntity findByNameAndRestaurant(final @Param("name") String name, final @Param("restaurant") RestaurantEntity restaurant);

    @Query("SELECT e FROM MealEntity e WHERE e.restaurant =  :restaurant")
    List<MealEntity> findAllByRestaurant(final @Param("restaurant") RestaurantEntity restaurant);
}
