package food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.StreetEntity;
import Food_app.infrastructure.database.repository.mapper.StreetEntityMapper;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StreetEntityMapperTest {

    private StreetEntityMapper streetEntityMapper;
    @BeforeEach
    public void setUp() {
        streetEntityMapper = Mappers.getMapper(StreetEntityMapper.class);
    }

        @Test
        void testStreetEntityToStreet() {
            StreetEntity streetEntity= SomeFixtures.someStreetEntity();
            Street street = streetEntityMapper.map(streetEntity);
            assertEquals(streetEntity.getName(), street.getName());
            streetEntity=null;
            street=streetEntityMapper.map(streetEntity);
            assertNull(street);
        }

    @Test
    void testStreetToStreetEntity() {
        Street street= SomeFixtures.someStreet();
        StreetEntity streetEntity = streetEntityMapper.map(street);
        assertEquals(street.getName(), streetEntity.getName());
        street=null;
        streetEntity=streetEntityMapper.map(street);
        assertNull(streetEntity);
    }


}
