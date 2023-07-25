package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.domain.Address;
import Food_app.domain.Customer;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.CustomerEntity;
import Food_app.infrastructure.database.repository.mapper.AddressEntityMapper;
import Food_app.infrastructure.database.repository.mapper.CustomerEntityMapper;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerEntityMapperTest {

    private CustomerEntityMapper mapper;
    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(CustomerEntityMapper.class);
    }
    @Test
    void mapCustomerToEntityTest() {
        // Given
        Customer customer = SomeFixtures.someCustomer();

        // When
        CustomerEntity customerEntity = mapper.map(customer);

        // Then
        assertEquals(customer.getCustomerId(), customerEntity.getCustomerId());
        assertEquals(customer.getPhone(), customerEntity.getPhone());
        assertEquals(customer.getSurname(), customerEntity.getSurname());
        assertEquals(customer.getName(), customerEntity.getName());
        assertEquals(customer.getUserName(), customerEntity.getUserName());
    }

    @Test
    void mapEntityToCustomerTest() {

        // When
        CustomerEntity customerEntity = SomeFixtures.someCustomerEntity();

        // When
        Customer customer = mapper.mapWithOrders(customerEntity);

        // Then
        assertEquals(customer.getCustomerId(), customerEntity.getCustomerId());
        assertEquals(customer.getPhone(), customerEntity.getPhone());
        assertEquals(customer.getSurname(), customerEntity.getSurname());
        assertEquals(customer.getName(), customerEntity.getName());
        assertEquals(customer.getUserName(), customerEntity.getUserName());

    }
    @Test
    void mapCustomerWithSomeNullsToEntityTest() {
        // Given
        Customer customer = Customer.builder().build();

        // When
        CustomerEntity customerEntity = mapper.map(customer);

        // Then
        assertNull(customerEntity.getCustomerId());
        assertNull(customerEntity.getPhone());
        assertNull(customerEntity.getSurname());
        assertNull(customerEntity.getName());
        assertNull(customerEntity.getUserName());

        customer=null;
        customerEntity=mapper.map(customer);
        assertNull(customerEntity);
    }

    @Test
    void mapEntityWithSomeNullsToCustomerTest() {

        // When
        CustomerEntity customerEntity = CustomerEntity.builder()
                .orders(Set.of())
                .build();

        // When
        Customer customer = mapper.mapWithOrders(customerEntity);

        // Then
        assertNull(customer.getCustomerId());
        assertNull(customer.getPhone());
        assertNull(customer.getSurname());
        assertNull(customer.getName());
        assertNull(customer.getUserName());

        customerEntity=null;
        customer=mapper.map(customerEntity);
        assertNull(customer);
    }
}
