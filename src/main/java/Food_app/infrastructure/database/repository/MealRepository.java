package Food_app.infrastructure.database.repository;

import Food_app.business.dao.MealDAO;
import Food_app.domain.Meal;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.repository.jpa.MealJpaRepository;
import Food_app.infrastructure.database.repository.mapper.MealEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MealRepository implements MealDAO {
    private final MealJpaRepository mealJpaRepository;
    private final MealEntityMapper mealEntityMapper;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void deleteById(Integer id) {
        mealJpaRepository.deleteById(id);
    }

    @Override
    public void createMeal(Meal meal) {
        mealJpaRepository.save(mealEntityMapper.mapWithRestaurant(meal));
    }

    @Override
    public Meal findByNameAndRestaurant(String mealName, Restaurant restaurant) {
        return mealEntityMapper
                .map(mealJpaRepository.findByNameAndRestaurant(mealName, restaurantEntityMapper.map(restaurant)));
    }

    @Override
    public void updateMeal(Meal meal) {
        mealJpaRepository.save(mealEntityMapper.mapWithRestaurant(meal));
    }

    @Override
    public List<Meal> findByRestaurant(Restaurant restaurant) {
        return mealJpaRepository
                .findAllByRestaurant(restaurantEntityMapper.map(restaurant)).stream()
                .map(mealEntityMapper::map)
                .toList();
    }
}
