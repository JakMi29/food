package Food_app.api.dto.mapper;

import Food_app.api.dto.OrderDetailsDTO;
import Food_app.domain.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:39+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class OrderDetailsMapperImpl implements OrderDetailsMapper {

    @Override
    public OrderDetailsDTO map(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDetailsDTO.OrderDetailsDTOBuilder orderDetailsDTO = OrderDetailsDTO.builder();

        orderDetailsDTO.price( bigDecimalMapper( order.getPrice() ) );
        orderDetailsDTO.receivedDateTime( mapOffsetDateTimeToString( order.getReceivedDateTime() ) );
        orderDetailsDTO.completedDateTime( mapOffsetDateTimeToString( order.getCompletedDateTime() ) );
        orderDetailsDTO.customer( customerToName( order.getCustomer() ) );
        orderDetailsDTO.restaurant( restaurantToName( order.getRestaurant() ) );
        orderDetailsDTO.complete( order.getComplete() );
        orderDetailsDTO.orderNumber( order.getOrderNumber() );

        return orderDetailsDTO.build();
    }
}
