package Food_app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodApiMealDetailsDTO {
    private String id;
    private String title;
    private String difficulty;
    private String portion;
    private String time;
    private String description;
    private List<String> ingredients;
    private Map<Integer, String> method;
    private String image;
}
