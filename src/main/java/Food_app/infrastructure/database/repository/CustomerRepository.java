package Food_app.infrastructure.database.repository;

import Food_app.business.dao.CustomerDAO;
import Food_app.domain.Customer;
import Food_app.infrastructure.database.repository.jpa.CustomerJpaRepository;
import Food_app.infrastructure.database.repository.mapper.CustomerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerEntityMapper customerMapper;

    @Override
    public void createCustomer(Customer customer) {
        customerJpaRepository.save(customerMapper.map(customer));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerJpaRepository.findByEmail(email).map(customerMapper::map);
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        return customerJpaRepository.findByPhone(phone).map(customerMapper::map);
    }

    @Override
    public Optional<Customer> findByUserName(String userName) {
        return customerJpaRepository.findByUserName(userName).map(customerMapper::map);
    }

    @Override
    public Optional<Customer> findByUserNameWithOrders(String userName) {
        return customerJpaRepository.findWithOrdersByUserName(userName).map(customerMapper::mapWithOrders);
    }
}
