package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.MealEntity;
import Food_app.infrastructure.database.entity.OrderMealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMealJpaRepository extends JpaRepository<OrderMealEntity, Integer> {

    void deleteAllByMeal(MealEntity entity);
}
