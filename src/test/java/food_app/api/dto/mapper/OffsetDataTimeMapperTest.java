package food_app.api.dto.mapper;

import Food_app.api.dto.mapper.SomeMappers;
import Food_app.api.dto.mapper.SomeMappersImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OffsetDataTimeMapperTest {

    @Test
    public void testMapOffsetDateTimeToString() {
        SomeMappers mapper=new SomeMappersImpl();

        OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 7, 4, 12, 0, 0, 0, ZoneOffset.UTC);
        String expected = "2023-07-04 12:00:00";

        String result = mapper.mapOffsetDateTimeToString(offsetDateTime);

        assertEquals(expected, result);
    }

    @Test
    public void testMapOffsetDateTimeToStringWithNull() {
        SomeMappers mapper = new SomeMappersImpl();

        String result = mapper.mapOffsetDateTimeToString(null);

        assertNull(result);
    }

    @Test
    public void testBigDecimalToStringMapper() {
        SomeMappers mapper = new SomeMappersImpl();

        BigDecimal bigDecimal = new BigDecimal("10.5");
        String expected = "10.5";

        String result = mapper.bigDecimalMapper(bigDecimal);

        assertEquals(expected, result);
    }

    @Test
    public void testStringToBigDecimalMapper() {
        SomeMappers mapper = new SomeMappersImpl();

        String string = "10.5";
        BigDecimal expected = new BigDecimal("10.5");

        BigDecimal result = mapper.StringToBigDecimalMapper(string);

        assertEquals(expected, result);
    }
}
