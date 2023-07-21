package Food_app.business.dao;

import Food_app.domain.Meal;
import Food_app.domain.Restaurant;

import java.util.List;

public interface MealDAO {
    void deleteById(Integer id);

    void createMeal(Meal meal);

    Meal findByNameAndRestaurant(String mealName, Restaurant restaurant);

    void updateMeal(Meal meal);

    List<Meal> findByRestaurant(Restaurant restaurant);

}
