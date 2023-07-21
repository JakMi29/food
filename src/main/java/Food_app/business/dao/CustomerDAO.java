package Food_app.business.dao;

import Food_app.domain.Customer;

import java.util.Optional;

public interface CustomerDAO {

    void createCustomer(Customer customer);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findByUserName(String userName);

    Optional<Customer> findByUserNameWithOrders(String userName);
}
