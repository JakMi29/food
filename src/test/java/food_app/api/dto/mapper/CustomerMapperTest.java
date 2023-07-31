package food_app.api.dto.mapper;

import Food_app.api.dto.CustomerDTO;
import Food_app.api.dto.mapper.CustomerMapper;
import Food_app.domain.Customer;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    public void setUp() {
        customerMapper = Mappers.getMapper(CustomerMapper.class);
    }

    @Test
    public void testMapCustomerToDTO() throws IOException {

        Customer customer = SomeFixtures.someCustomer();

        CustomerDTO result = customerMapper.map(customer);

        assertEquals(customer.getName(),result.getName());
        assertEquals(customer.getSurname(),result.getSurname());
        assertEquals(customer.getUserName(),result.getUserName());

        customer=null;
        result=customerMapper.map(customer);
        assertNull(result);

    }

    @Test
    public void testMapWithOrders() {
        Customer customer = SomeFixtures.someCustomer();

        CustomerDTO customerDTO=customerMapper.mapWithOrders(customer);
        assertEquals(customer.getName(),customerDTO.getName());
}


}
