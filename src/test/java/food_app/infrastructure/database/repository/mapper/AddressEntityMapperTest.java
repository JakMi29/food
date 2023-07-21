package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Address;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.repository.mapper.AddressEntityMapper;
import food_app.integration.configuration.PersistenceContainerTestConfiguration;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressEntityMapperTest {

    private AddressEntityMapper mapper;
    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(AddressEntityMapper.class);
    }
    @Test
    void MapAddressToAddressEntityCorrectly() {
        // Given
        Address address = SomeFixtures.someAddress();

        // When
        AddressEntity addressEntity = mapper.map(address);

        // Then
        assertEquals(address.getStreet().getStreetId(), addressEntity.getStreet().getStreetId());
        assertEquals(address.getStreet().getName(), addressEntity.getStreet().getName());
        assertEquals(address.getCountry(), addressEntity.getCountry());
        assertEquals(address.getAddressId(), addressEntity.getAddressId());
        assertEquals(address.getCity(), addressEntity.getCity());
        assertEquals(address.getPostalCode(), addressEntity.getPostalCode());
        assertEquals(address.getHouseNumber(), addressEntity.getHouseNumber());
    }

    @Test
    void MapAddressEntityToAddressCorrectly() {
        AddressEntity addressEntity = SomeFixtures.someAddressEntity();

        // When
        Address address = mapper.map(addressEntity);

        // Then
        assertEquals(addressEntity.getStreet().getStreetId(), address.getStreet().getStreetId());
        assertEquals(addressEntity.getStreet().getName(), address.getStreet().getName());
        assertEquals(addressEntity.getCountry(), address.getCountry());
        assertEquals(addressEntity.getAddressId(), address.getAddressId());
        assertEquals(addressEntity.getCity(), address.getCity());
        assertEquals(addressEntity.getPostalCode(), address.getPostalCode());
        assertEquals(addressEntity.getHouseNumber(), address.getHouseNumber());

    }
}
