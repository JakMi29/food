package food_app.infrastructure.database.repository;

import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantStreet;
import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.entity.RestaurantStreetEntity;
import Food_app.infrastructure.database.entity.StreetEntity;
import Food_app.infrastructure.database.repository.RestaurantStreetRepository;
import Food_app.infrastructure.database.repository.jpa.RestaurantStreetJpaRepository;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantStreetEntityMapper;
import Food_app.infrastructure.database.repository.mapper.StreetEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class RestaurantStreetRepositoryTest {
    @Mock
    private RestaurantStreetJpaRepository mockJpaRepository;

    @Mock
    private RestaurantStreetEntityMapper mockEntityMapper;

    @Mock
    private RestaurantEntityMapper mockRestaurantEntityMapper;

    @Mock
    private StreetEntityMapper mockStreetEntityMapper;
@InjectMocks
    private RestaurantStreetRepository restaurantStreetRepository;


    @Test
    void testFindRestaurantStreetsByRestaurant() {
        Restaurant restaurant = SomeFixtures.someRestaurant();
        List<RestaurantStreetEntity> entities = Collections.singletonList(SomeFixtures.someRestaurantStreetEntity());
        List<RestaurantStreet> expectedStreets = Collections.singletonList(SomeFixtures.someRestaurantStreet());

        when(mockRestaurantEntityMapper.map(restaurant)).thenReturn(SomeFixtures.someRestaurantEntity());
        when(mockJpaRepository.findByRestaurant(any(RestaurantEntity.class))).thenReturn(entities);
        when(mockEntityMapper.mapWithRestaurant(any(RestaurantStreetEntity.class))).thenReturn(SomeFixtures.someRestaurantStreet());

        List<RestaurantStreet> result = restaurantStreetRepository.findRestaurantStreetsByRestaurant(restaurant);

        verify(mockJpaRepository, times(1)).findByRestaurant(any(RestaurantEntity.class));

        assertEquals(expectedStreets, result);
    }

    @Test
    void testRemoveStreetFromRestaurant() {
        Street street = SomeFixtures.someStreet();
        Restaurant restaurant = SomeFixtures.someRestaurant();

        when(mockRestaurantEntityMapper.map(restaurant)).thenReturn(SomeFixtures.someRestaurantEntity());
        when(mockStreetEntityMapper.map(street)).thenReturn(SomeFixtures.someStreetEntity());
        restaurantStreetRepository.removeStreetFromRestaurant(street, restaurant);

        verify(mockJpaRepository, times(1)).removeByStreetAndRestaurant(any(StreetEntity.class), any(RestaurantEntity.class));
    }

    @Test
    void testFindAllByStreet() {
        Street street = SomeFixtures.someStreet();
        List<RestaurantStreetEntity> entities = Collections.singletonList(SomeFixtures.someRestaurantStreetEntity());
        List<RestaurantStreet> expectedStreets = Collections.singletonList(SomeFixtures.someRestaurantStreet());

        when(mockStreetEntityMapper.map(street)).thenReturn(SomeFixtures.someStreetEntity());
        when(mockJpaRepository.findByStreet(any(StreetEntity.class))).thenReturn(entities);
        when(mockEntityMapper.mapWithRestaurant(any(RestaurantStreetEntity.class))).thenReturn(SomeFixtures.someRestaurantStreet());

        List<RestaurantStreet> result = restaurantStreetRepository.findAllByStreet(street);

        verify(mockJpaRepository, times(1)).findByStreet(any(StreetEntity.class));

        assertEquals(expectedStreets, result);
    }

    @Test
    void testCreateRestaurantStreet() {
        Street street = SomeFixtures.someStreet();
        Restaurant restaurant = SomeFixtures.someRestaurant();

        when(mockStreetEntityMapper.map(street)).thenReturn(SomeFixtures.someStreetEntity());
        when(mockRestaurantEntityMapper.map(restaurant)).thenReturn(SomeFixtures.someRestaurantEntity());

        restaurantStreetRepository.createRestaurantStreet(street, restaurant);

        verify(mockJpaRepository, times(1)).save(any(RestaurantStreetEntity.class));
    }
}