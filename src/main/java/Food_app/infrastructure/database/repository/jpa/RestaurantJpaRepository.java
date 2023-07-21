package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.RestaurantEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Integer> {
    @EntityGraph(value = "Restaurant.meals")
    Optional<RestaurantEntity> findById(@NonNull Integer id);

    @Query("SELECT r FROM RestaurantEntity r LEFT JOIN r.meals m WHERE r.name = :name")
    Optional<RestaurantEntity> findWithMealsByName(@NonNull String name);

    @Query("SELECT r FROM RestaurantEntity r LEFT JOIN r.orders o WHERE r.name = :name")
    Optional<RestaurantEntity> findWithOrdersByName(@NonNull String name);

    Page<RestaurantEntity> findAll(Pageable pageable);

    Optional<RestaurantEntity> findByName(String name);
}
