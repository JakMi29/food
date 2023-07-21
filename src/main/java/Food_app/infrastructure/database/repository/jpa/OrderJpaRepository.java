package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.OrderEntity;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("SELECT e FROM OrderEntity e WHERE e.restaurant =  :restaurant")
    List<OrderEntity> findAllByRestaurant(final @Param("restaurant") RestaurantEntity restaurant);


    @EntityGraph(attributePaths = {"customer", "restaurant"})
    Optional<OrderEntity> findByOrderNumber(String orderName);

    @Query("SELECT o FROM OrderEntity o LEFT JOIN FETCH o.orderMeals WHERE o.orderNumber= :orderName")
    OrderEntity findByOrderNumberWithRelatedOrderMeals(String orderName);
}
