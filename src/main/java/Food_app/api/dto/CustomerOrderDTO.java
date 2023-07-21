package Food_app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDTO {
    String price;
    Boolean complete;
    String orderNumber;
    Boolean cancelable;
    String restaurantName;
}
