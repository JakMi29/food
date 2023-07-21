package Food_app.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CreateRestaurantOwnerDTO {
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
    @Email
    String email;
    @NotBlank
    String street;
    @NotBlank
    String homeNumber;
    @NotBlank
    String password;
    @NotBlank
    String restaurantName;
    @NotBlank
    String foodCategory;
    @Email
    @NotBlank
    String restaurantEmail;
    @Size
    @NotBlank
    @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$")
    String restaurantPhone;
}
