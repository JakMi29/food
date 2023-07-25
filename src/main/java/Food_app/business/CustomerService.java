package Food_app.business;

import Food_app.api.dto.CreateCustomerDTO;
import Food_app.business.dao.CustomerDAO;
import Food_app.domain.Address;
import Food_app.domain.Customer;
import Food_app.domain.Street;
import Food_app.domain.exception.NotFoundException;
import Food_app.domain.exception.UserAlreadyExist;
import Food_app.infrastructure.security.RoleEntity;
import Food_app.infrastructure.security.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor

public class CustomerService {
    private final CustomerDAO customerDAO;
    private final StreetService streetService;

    @Transactional
    public Customer findCustomerByUserName(String userName) {
        Optional<Customer> customer = customerDAO.findByUserName(userName);
        if (customer.isEmpty()) {
            log.error("Could not found customer by user name: [%s]".formatted(userName));
            throw new NotFoundException("Could not found customer by user name: [%s]".formatted(userName));
        }
        return customer.get();
    }

    @Transactional
    public Customer findCustomerByUserNameWithOrders(String userName) {
        Optional<Customer>customer= customerDAO.findByUserNameWithOrders(userName);
        if (customer.isEmpty()) {
            log.error("Could not found customer by user name: [%s]".formatted(userName));
            throw new NotFoundException("Could not found customer by user name: [%s]".formatted(userName));
        }
        return customer.get();
    }

    @Transactional
    public void createCustomer(CreateCustomerDTO customer) {

        checkIfCustomerWithUniqueVariablesExist(customer);

        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .userName(customer.getUserName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(buildAddress(customer))
                .user(buildUser(customer))
                .build();

        customerDAO.createCustomer(newCustomer);
        log.info("successful created customer [%s]".formatted(newCustomer));
    }

    @Transactional
    void checkIfCustomerWithUniqueVariablesExist(CreateCustomerDTO customer) {
        if (customerDAO.findByEmail(customer.getEmail()).isPresent()) {
            log.error("User with this email already exist");
            throw new UserAlreadyExist("User with this email already exist");
        } else if (customerDAO.findByPhone(customer.getPhone()).isPresent()) {
            log.error("User with this phone already exist");
            throw new UserAlreadyExist("User with this phone already exist");
        } else if (customerDAO.findByUserName(customer.getUserName()).isPresent()) {
            log.error("User with this user name already exist");
            throw new UserAlreadyExist("User with this user name already exist");
        }
    }

    private Address buildAddress(CreateCustomerDTO customer) {
        Street street = streetService.findOrCreateStreet(customer.getStreet());
        return Address.builder()
                .city(customer.getCity())
                .country(customer.getCountry())
                .postalCode(customer.getPostalCode())
                .street(street)
                .houseNumber(customer.getHomeNumber())
                .build();
    }

    private User buildUser(CreateCustomerDTO customer) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .userName(customer.getUserName())
                .email(customer.getEmail())
                .password(passwordEncoder.encode(customer.getPassword()))
                .active(true)
                .role(RoleEntity.builder().id(1).build())
                .build();
    }
}
