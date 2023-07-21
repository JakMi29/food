package Food_app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RestaurantDTO {
    String name;
    String foodCategory;
    String phone;
    List<MealDTO> meals;
    List<RestaurantOrderDTO> orders;
}
