package Food_app.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface BigDecimalMapper {


    @Named("bigDecimalToStringMapper")
    default String bigDecimalMapper(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }

    @Named("stringToBigDecimalMapper")
    default BigDecimal StringToBigDecimalMapper(String string) {
        return new BigDecimal(string);
    }
}

