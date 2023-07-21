package Food_app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@Value
@With
@Builder
@EqualsAndHashCode

public class Meal {

    Integer mealId;
    String name;
    BigDecimal price;
    String category;
    String description;
    Restaurant restaurant;
    String image;
}

