package food_app.infrastructure.database.entity;

import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.StreetEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddressEntityTest {
    @Test
    public void testEqualsAndHashCode() {
        // Create two instances with the same attribute values
        StreetEntity street = StreetEntity.builder()
                .name("Test Street")
                .build();

        AddressEntity address1 = AddressEntity.builder()
                .country("Test Country")
                .city("Test City")
                .postalCode("12345")
                .houseNumber("42")
                .street(street)
                .build();

        AddressEntity address2 = AddressEntity.builder()
                .country("Test Country")
                .city("Test City")
                .postalCode("12345")
                .houseNumber("42")
                .street(street)
                .build();

        // Test equals method
        Assertions.assertEquals(address1, address1); // Equality with the same instance
        Assertions.assertEquals(address1, address2); // Equality with another instance of the same class
        Assertions.assertEquals(address2, address1); // Check symmetry

        // Test hashCode method
        Assertions.assertEquals(address1.hashCode(), address2.hashCode());

        // Create an instance with different attribute values
        AddressEntity differentAddress = AddressEntity.builder()
                .country("Different Country")
                .city("Different City")
                .postalCode("54321")
                .houseNumber("1A")
                .street(street)
                .build();

        // Test equals method with a different instance
        Assertions.assertNotEquals(address1, differentAddress); // Equality with a different attribute value
        Assertions.assertNotEquals(differentAddress, address1); // Check symmetry

        // Test equals method with null
        Assertions.assertNotEquals(address1, null);

        // Test equals method with a different type
        Assertions.assertNotEquals(address1, "Test");

        // Test equals method with an instance of a subclass (if applicable)
        // For this specific class, this case may not be applicable, but it depends on your inheritance hierarchy.

        // Test hashCode method with a different instance
        Assertions.assertNotEquals(address1.hashCode(), differentAddress.hashCode());
    }
}
