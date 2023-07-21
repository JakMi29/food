package Food_app.api.dto.mapper;

import Food_app.api.dto.CustomerDTO;
import Food_app.api.dto.CustomerOrderDTO;
import Food_app.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(target = "orders", ignore = true)
    CustomerDTO map(final Customer customer);

    default CustomerDTO mapWithOrders(final Customer customer) {
        CustomerDTO customerDTO = map(customer);
        customerDTO.setOrders(
                customer.getOrders().stream()
                        .map(c -> CustomerOrderDTO.builder()
                                .price(c.getPrice().toString())
                                .cancelable(c.getReceivedDateTime().plusMinutes(20).isAfter(OffsetDateTime.now()) && !c.getComplete())
                                .orderNumber(c.getOrderNumber())
                                .restaurantName(c.getRestaurant().getName())
                                .complete(c.getComplete())
                                .build())
                        .toList());
        return customerDTO;
    }

}
