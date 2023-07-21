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
public class OrderDetailsDTO {
    String price;
    Boolean complete;
    String orderNumber;
    String customer;
    String restaurant;
    String receivedDateTime;
    String completedDateTime;
    List<OrderMealDTO> meals;
}
