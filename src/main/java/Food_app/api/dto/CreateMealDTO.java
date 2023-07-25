package Food_app.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealDTO {
    @NotBlank
    String name;
    @NotBlank
    String category;
    @NotBlank
    String description;
    @NotBlank
    String price;
    MultipartFile image;
}
