package Food_app.infrastructure.database.repository;

import Food_app.business.dao.OrderMealDAO;
import Food_app.domain.Meal;
import Food_app.infrastructure.database.repository.jpa.OrderMealJpaRepository;
import Food_app.infrastructure.database.repository.mapper.MealEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderMealRepository implements OrderMealDAO {
    OrderMealJpaRepository orderMealJpaRepository;
    MealEntityMapper mealEntityMapper;

    public void deleteAllByMeal(Meal meal) {
        orderMealJpaRepository.deleteAllByMeal(mealEntityMapper.map(meal));
    }

}
