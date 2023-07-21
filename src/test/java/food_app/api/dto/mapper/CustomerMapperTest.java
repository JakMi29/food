package food_app.api.dto.mapper;

import Food_app.api.dto.CreateMealDTO;
import Food_app.api.dto.CustomerDTO;
import Food_app.api.dto.mapper.*;
import Food_app.domain.Customer;
import Food_app.domain.Meal;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    }

    @Test
    public void testMapDTOToCustomer() throws IOException {

        Customer customer = SomeFixtures.someCustomer();

        CustomerDTO result = customerMapper.map(customer);

        assertEquals(customer.getName(),result.getName());
        assertEquals(customer.getSurname(),result.getSurname());
        assertEquals(customer.getUserName(),result.getUserName());

    }

    @Test
    public void testMapWithOrders() {
        Customer customer = SomeFixtures.someCustomer();

        CustomerDTO customerDTO=customerMapper.mapWithOrders(customer);



}


}
