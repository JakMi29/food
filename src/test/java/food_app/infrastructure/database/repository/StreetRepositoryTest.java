package food_app.infrastructure.database.repository;

import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.StreetEntity;
import Food_app.infrastructure.database.repository.StreetRepository;
import Food_app.infrastructure.database.repository.jpa.StreetJpaRepository;
import Food_app.infrastructure.database.repository.mapper.StreetEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class StreetRepositoryTest {
    @Mock
    private StreetJpaRepository streetJpaRepository;
    @Mock
    private StreetEntityMapper streetEntityMapper;

@InjectMocks
    private StreetRepository streetRepository;

    @Test
    void testCreateStreet() {
        Street street = SomeFixtures.someStreet();

        when(streetEntityMapper.map(street)).thenReturn(SomeFixtures.someStreetEntity());

        streetRepository.createStreet(street);
        verify(streetJpaRepository, times(1)).save(any(StreetEntity.class));
    }

    @Test
    void testFindStreetByName() {
        String streetName = "Main Street";
        StreetEntity streetEntity = SomeFixtures.someStreetEntity();
        Street expectedStreet = SomeFixtures.someStreet();


        when(streetJpaRepository.findByName(streetName)).thenReturn(Optional.of(streetEntity));
        when(streetEntityMapper.map(streetEntity)).thenReturn(expectedStreet);


        Optional<Street> result = streetRepository.findStreetByName(streetName);


        verify(streetJpaRepository, times(1)).findByName(streetName);


        assertEquals(Optional.of(expectedStreet), result);
    }
}