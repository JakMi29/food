package Food_app.api.dto;

import Food_app.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOrderDTO {
    String price;
    Boolean complete;
    String orderNumber;
    Customer customer;
}
