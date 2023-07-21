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

public class RestaurantMenuDTO {
    String name;
    String foodCategory;
    String phone;
    String description;
    String address;
    List<MealDTO> meals;
}
