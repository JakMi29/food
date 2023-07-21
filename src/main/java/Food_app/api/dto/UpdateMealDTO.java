package Food_app.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMealDTO {
    @NotBlank
    String name;
    @NotBlank
    String category;
    @NotBlank
    String description;
    @NotBlank
    String price;
}
