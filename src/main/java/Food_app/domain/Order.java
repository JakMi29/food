package Food_app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Value
@With
@Builder
@EqualsAndHashCode

public class Order {
    Integer orderId;
    BigDecimal price;
    Boolean complete;
    String orderNumber;
    OffsetDateTime receivedDateTime;
    OffsetDateTime completedDateTime;
    Restaurant restaurant;
    Customer customer;
    Set<OrderMeal> orderMeals;
}
