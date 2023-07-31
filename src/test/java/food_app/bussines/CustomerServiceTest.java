package food_app.bussines;

import Food_app.api.dto.CreateCustomerDTO;
import Food_app.business.CustomerService;
import Food_app.business.StreetService;
import Food_app.business.dao.CustomerDAO;
import Food_app.domain.Customer;
import Food_app.domain.exception.NotFoundException;
import Food_app.domain.exception.UserAlreadyExist;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerDAO customerDAO;
    @Mock
    private StreetService streetService;

    @Test
    public void testFindCustomerByUserName() {
        Customer customer = SomeFixtures.someCustomer();

        when(customerDAO.findByUserName("john"))
                .thenReturn(Optional.of(customer))
                .thenReturn(Optional.empty());

        Customer result = customerService.findCustomerByUserName("john");
        assertEquals(customer, result);

        Assertions.assertThrows(NotFoundException.class, () -> customerService.findCustomerByUserName("customer"));
    }

    @Test
    void testCreateCustomer() {
        CreateCustomerDTO customerDTO = SomeFixtures.someCreateCustomerDTO();
        Customer customer = SomeFixtures.someCustomer();

        when(customerDAO.findByEmail(anyString()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(customer))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.empty());

        when(customerDAO.findByPhone(anyString()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(customer))
                .thenReturn(Optional.empty());
        when(customerDAO.findByUserName(anyString()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(customer));

        customerService.createCustomer(customerDTO);
        Assertions.assertThrows(UserAlreadyExist.class, () -> customerService.createCustomer(customerDTO));
        Assertions.assertThrows(UserAlreadyExist.class, () -> customerService.createCustomer(customerDTO));
        Assertions.assertThrows(UserAlreadyExist.class, () -> customerService.createCustomer(customerDTO));


        verify(customerDAO, times(4)).findByEmail(customerDTO.getEmail());
        verify(customerDAO, times(3)).findByPhone(customerDTO.getPhone());
        verify(customerDAO, times(2)).findByUserName(customerDTO.getUserName());
    }

    @Test
    public void testFindCustomerByUserNameWithOrders() {
        Customer customer = SomeFixtures.someCustomer();

        when(customerDAO.findByUserNameWithOrders("john"))
                .thenReturn(Optional.of(customer))
                .thenReturn(Optional.empty());


        Customer result = customerService.findCustomerByUserNameWithOrders("john");
        assertEquals(customer, result);
        Assertions.assertThrows(NotFoundException.class, () -> customerService.findCustomerByUserNameWithOrders("customer"));
    }
}
