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

public class OrderMeal {

    Integer orderMealId;
    Integer quantity;
    BigDecimal price;
    Order order;
    Meal meal;
}
