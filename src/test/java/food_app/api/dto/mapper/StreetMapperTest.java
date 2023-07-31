package food_app.api.dto.mapper;

import Food_app.api.dto.StreetDTO;
import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Street;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class
StreetMapperTest {

    private StreetMapper streetMapper;
    @BeforeEach
    public void setUp() {
        streetMapper = Mappers.getMapper(StreetMapper.class);
    }
    @Test
    void testStreetDTOToStreet() {
        StreetDTO streetDTO = SomeFixtures.someStreetDto();
        Street street = streetMapper.map(streetDTO);
        assertEquals(streetDTO.getName(), street.getName());
        street=streetMapper.map(null);
        assertNull(street);
        streetDTO=StreetDTO.builder().build();
        street=streetMapper.map(streetDTO);
        assertNull(street.getName());
    }
}
