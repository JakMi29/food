package Food_app.api.dto.mapper;

import Food_app.api.dto.RestaurantOrderDTO;
import Food_app.domain.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class RestaurantOrderMapperImpl implements RestaurantOrderMapper {

    @Override
    public RestaurantOrderDTO map(Order order) {
        if ( order == null ) {
            return null;
        }

        RestaurantOrderDTO.RestaurantOrderDTOBuilder restaurantOrderDTO = RestaurantOrderDTO.builder();

        restaurantOrderDTO.price( bigDecimalMapper( order.getPrice() ) );
        restaurantOrderDTO.complete( order.getComplete() );
        restaurantOrderDTO.orderNumber( order.getOrderNumber() );
        restaurantOrderDTO.customer( order.getCustomer() );

        return restaurantOrderDTO.build();
    }
}
