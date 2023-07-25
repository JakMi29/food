package Food_app.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreateCustomerDTO {
    @NotBlank
    String name;
    @NotBlank
    String surname;
    @NotBlank
    String userName;
    @NotBlank
    @Size
    @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$")
    String phone;
    @NotBlank
    String country;
    @NotBlank
    String city;
    @NotBlank
    String postalCode;
    @NotBlank
    @Email
    String email;
    @NotBlank
    String street;
    @NotBlank
    String homeNumber;
    @NotBlank
    String password;

}
