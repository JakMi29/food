package Food_app.business;

import Food_app.business.dao.OrderMealDAO;
import Food_app.domain.Meal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderMealService {
    OrderMealDAO orderMealDAO;

    @Transactional
    public void deleteAllByMeal(Meal meal) {
        orderMealDAO.deleteAllByMeal(meal);
    }
}
