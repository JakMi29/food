package Food_app.business.dao;

import Food_app.domain.Meal;

public interface OrderMealDAO {
    void deleteAllByMeal(Meal meal);

}
