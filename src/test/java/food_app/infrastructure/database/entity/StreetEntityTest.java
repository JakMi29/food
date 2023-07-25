package food_app.infrastructure.database.entity;

import Food_app.infrastructure.database.entity.StreetEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StreetEntityTest {
    @Test
    public void testEqualsAndHashCode() {
        // Test 1: Porównywanie z samym sobą
        StreetEntity street1 = StreetEntity.builder()
                .streetId(1)
                .name("Main Street")
                .build();
        assertEquals(street1, street1);
        assertEquals(street1.hashCode(), street1.hashCode());

        // Test 2: Porównywanie z innym obiektem tego samego typu o tych samych polach
        StreetEntity street2 = StreetEntity.builder()
                .streetId(1)
                .name("Main Street")
                .build();
        assertEquals(street1, street2);
        assertEquals(street1.hashCode(), street2.hashCode());

        // Test 3: Porównywanie z obiektem innego typu
        String notAStreet = "This is not a street";
        assertNotEquals(street1, notAStreet);

        // Test 4: Porównywanie z null
        assertNotEquals(null, street1);

        // Test 5: Porównywanie z obiektem zawierającym różne wartości pól
        StreetEntity street3 = StreetEntity.builder()
                .streetId(2)
                .name("Park Avenue")
                .build();
        assertNotEquals(street1, street3);
    }

    @Test
    public void testEqualsAndHashCodeConsistency() {
        StreetEntity street1 = StreetEntity.builder()
                .streetId(1)
                .name("Main Street")
                .build();
        StreetEntity street2 = StreetEntity.builder()
                .streetId(1)
                .name("Main Street")
                .build();

        // Spójność między equals() a hashCode()
        assertEquals(street1, street2);
        assertEquals(street1.hashCode(), street2.hashCode());
    }
}
