package Food_app.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="orderNumber")
public class CustomerOrderDTO {
    String price;
    Boolean complete;
    String orderNumber;
    Boolean cancelable;
    String restaurantName;
}
