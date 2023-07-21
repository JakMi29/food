package Food_app.api.dto.mapper;

import Food_app.domain.Address;
import Food_app.domain.Customer;
import Food_app.domain.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface SomeMappers {

    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Named("addressToString")
    default String addressToString(Address address) {
        String sb = address.getCity() + " " +
                address.getStreet().getName() + " " +
                address.getHouseNumber();
        return sb;
    }

    @Named("mapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return Optional.ofNullable(offsetDateTime)
                .map(odt -> offsetDateTime)
                .map(odt -> odt.format(DATE_FORMAT))
                .orElse(null);
    }

    @Named("listToStringMapper")
    default String listToStringMapper(Map<Integer, String> map) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            result.append(entry.getKey()).append(".").append(entry.getValue()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Named("bigDecimalToStringMapper")
    default String bigDecimalMapper(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }

    @Named("stringToBigDecimalMapper")
    default BigDecimal StringToBigDecimalMapper(String string) {
        return new BigDecimal(string);
    }

    @Named("customerToName")
    default String customerToName(Customer customer) {
        return customer.getName();
    }

    @Named("restaurantToName")
    default String restaurantToName(Restaurant restaurant) {
        return restaurant.getName();
    }
}
