package Food_app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@Value
@With
@Builder
@EqualsAndHashCode

public class CreateCustomerForm {

    String name;
    String surname;
    String userName;
    String phone;
    String city;
    String postalCode;
    String email;
    String street;
    String homeNumber;
    String password;
}
