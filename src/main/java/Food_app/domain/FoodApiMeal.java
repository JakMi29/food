package Food_app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FoodApiMeal {
    private Integer id;
    private String title;
    private String difficulty;
    private String image;
}
