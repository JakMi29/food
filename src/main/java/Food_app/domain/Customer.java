package Food_app.domain;

import Food_app.infrastructure.security.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

import java.util.Set;

@Value
@With
@Builder
@EqualsAndHashCode

public class Customer {

    Integer customerId;
    String name;
    String surname;
    String userName;
    String phone;
    String email;
    Address address;
    User user;
    Set<Order> orders;
}
