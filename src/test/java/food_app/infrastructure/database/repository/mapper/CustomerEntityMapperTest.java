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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
