package food_app.infrastructure.database.repository;


import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.repository.RestaurantRepository;
import Food_app.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantRepositoryTest {
    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;

    @Mock
    private RestaurantEntityMapper restaurantEntityMapper;

    @Mock
    private Page<RestaurantEntity> restaurantPage;

    @Mock
    private Pageable restaurantPageable;
    @InjectMocks
    private RestaurantRepository restaurantRepository;


    @Test
    void testFindAll() {
        List<RestaurantEntity> entities = Collections.singletonList(SomeFixtures.someRestaurantEntity());
        List<Restaurant> expectedRestaurants = Collections.singletonList(SomeFixtures.someRestaurant());

        when(restaurantJpaRepository.findAll()).thenReturn(entities);
        when(restaurantEntityMapper.map(any(RestaurantEntity.class))).thenReturn(SomeFixtures.someRestaurant());

        List<Restaurant> result = restaurantRepository.findAll();

        verify(restaurantJpaRepository, times(1)).findAll();

        assertEquals(expectedRestaurants, result);
    }

    @Test
    void testFindById() {
        Integer restaurantId = 1;
        RestaurantEntity restaurantEntity = SomeFixtures.someRestaurantEntity();
        Restaurant expectedRestaurant = SomeFixtures.someRestaurant();

        when(restaurantJpaRepository.findById(restaurantId)).thenReturn(Optional.of(restaurantEntity));
        when(restaurantEntityMapper.map(restaurantEntity)).thenReturn(expectedRestaurant);

        Optional<Restaurant> result = restaurantRepository.findById(restaurantId);

        verify(restaurantJpaRepository, times(1)).findById(restaurantId);

        assertEquals(Optional.of(expectedRestaurant), result);
    }

    @Test
    void testFindByNameWithMeals() {
        String restaurantName = "Restaurant";
        RestaurantEntity restaurantEntity = SomeFixtures.someRestaurantEntity();
        Restaurant expectedRestaurant = SomeFixtures.someRestaurant();

        when(restaurantJpaRepository.findWithMealsByName(restaurantName)).thenReturn(Optional.of(restaurantEntity));
        when(restaurantEntityMapper.mapWithMeals(restaurantEntity)).thenReturn(expectedRestaurant);

        Optional<Restaurant> result = restaurantRepository.findByNameWithMeals(restaurantName);

        verify(restaurantJpaRepository, times(1)).findWithMealsByName(restaurantName);

        assertEquals(Optional.of(expectedRestaurant), result);
    }

    @Test
    void testFindByNameWithOrders() {
        String restaurantName = "Restaurant";
        RestaurantEntity restaurantEntity = SomeFixtures.someRestaurantEntity();
        Restaurant expectedRestaurant = SomeFixtures.someRestaurant();

        when(restaurantJpaRepository.findWithOrdersByName(restaurantName)).thenReturn(Optional.of(restaurantEntity));
        when(restaurantEntityMapper.mapWithOrders(restaurantEntity)).thenReturn(expectedRestaurant);

        Optional<Restaurant> result = restaurantRepository.findByNameWithOrders(restaurantName);

        verify(restaurantJpaRepository, times(1)).findWithOrdersByName(restaurantName);

        assertEquals(Optional.of(expectedRestaurant), result);
    }

    @Test
    void testCreateRestaurant() {
        Restaurant restaurant = SomeFixtures.someRestaurant();
        when(restaurantEntityMapper.map(restaurant)).thenReturn(SomeFixtures.someRestaurantEntity());
        restaurantRepository.createRestaurant(restaurant);

        verify(restaurantJpaRepository, times(1)).save(any(RestaurantEntity.class));
    }

    @Test
    void testSaveRestaurant() {
        RestaurantEntity restaurantEntity = SomeFixtures.someRestaurantEntity();

        restaurantRepository.saveRestaurant(restaurantEntity);

        verify(restaurantJpaRepository, times(1)).save(restaurantEntity);
    }

    @Test
    void testFindByName() {
        String restaurantName = "Restaurant";

        when(restaurantJpaRepository.findByName(restaurantName)).thenReturn(Optional.of(SomeFixtures.someRestaurantEntity()));
        when(restaurantEntityMapper.map(SomeFixtures.someRestaurantEntity())).thenReturn((SomeFixtures.someRestaurant()));

        restaurantRepository.findByName(restaurantName);

        verify(restaurantJpaRepository, times(1)).findByName(restaurantName);

    }

}