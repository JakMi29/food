package Food_app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@Value
@With
@Builder
@EqualsAndHashCode
public class Address {

    Integer addressId;
    String country;
    String city;
    String postalCode;
    String address;
    Street street;
    String houseNumber;
}
