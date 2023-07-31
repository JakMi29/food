package food_app.bussines;

import Food_app.business.StreetService;
import Food_app.domain.Street;
import Food_app.infrastructure.database.repository.StreetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StreetServiceTest {

    @Mock
    private StreetRepository streetRepository;
    @InjectMocks
    private StreetService streetService;


    @Test
    void testCreateStreet() {
        // Given
        String streetName = "Mterwr";
        Street street = Street.builder().name(streetName).build();

        streetService.createStreet(streetName);

        // Then
        verify(streetRepository).createStreet(street);
    }

    @Test
    void testFindStreet_existingStreet() {
        // Given
        String streetName = "Main Street";
        Street street = Street.builder().name(streetName).build();

        // Mocking dependency
        when(streetRepository.findStreetByName(streetName)).thenReturn(Optional.of(street));

        // When
        Optional<Street> result = streetService.findStreet(streetName);

        // Then
        assertTrue(result.isPresent());
        assertEquals(street, result.get());
        verify(streetRepository).findStreetByName(streetName);
    }


    @Test
    void testFindOrCreateStreetExistingStreet() {
        // Given
        String streetName1 = "Main Street1";
        String streetName2 = "Main Street2";
        Street street1 = Street.builder().name(streetName1).build();
        Street street2 = Street.builder().name(streetName2).build();

        // Mocking dependency
        when(streetService.findStreet(streetName1)).thenReturn(Optional.of(street1));
        when(streetService.findStreet(streetName2)).thenReturn(Optional.empty()).thenReturn(Optional.of(street2));

        // When
        Street result1 = streetService.findOrCreateStreet(streetName1);
        Street result2 = streetService.findOrCreateStreet(streetName2);

        // Then
        assertEquals(street1, result1);
        assertEquals(street2,result2);
        verify(streetRepository, times(3)).findStreetByName(any());
        verify(streetRepository, times(1)).createStreet(any(Street.class));
    }

}
