package Food_app.infrastructure.security;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@Value
@With
@Builder
@EqualsAndHashCode

public class User {

    int id;
    String userName;
    String email;
    String password;
    Boolean active;
    RoleEntity role;
}
