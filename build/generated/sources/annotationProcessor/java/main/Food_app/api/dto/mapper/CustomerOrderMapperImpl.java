package Food_app.api.dto.mapper;

import Food_app.api.dto.CustomerOrderDTO;
import Food_app.domain.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class CustomerOrderMapperImpl implements CustomerOrderMapper {

    @Override
    public CustomerOrderDTO map(Order order) {
        if ( order == null ) {
            return null;
        }

        CustomerOrderDTO.CustomerOrderDTOBuilder customerOrderDTO = CustomerOrderDTO.builder();

        customerOrderDTO.price( bigDecimalMapper( order.getPrice() ) );
        customerOrderDTO.restaurantName( restaurantToName( order.getRestaurant() ) );
        customerOrderDTO.complete( order.getComplete() );
        customerOrderDTO.orderNumber( order.getOrderNumber() );

        return customerOrderDTO.build();
    }
}
