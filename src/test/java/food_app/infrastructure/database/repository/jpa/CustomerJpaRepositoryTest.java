package food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.CustomerEntity;
import Food_app.infrastructure.database.repository.jpa.CustomerJpaRepository;
import food_app.integration.configuration.PersistenceContainerTestConfiguration;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerJpaRepositoryTest {

    private CustomerJpaRepository customerJpaRepository;

    @Test
    void findByUserNameTest() {
        Optional<CustomerEntity> customerJohn = customerJpaRepository.findByUserName("customerJohn");
        Assertions.assertTrue(customerJohn.isPresent());
        Assertions.assertEquals(customerJohn.get().getUserName(),"customerJohn");
    }
    @Test
    void findByEmailTest() {
        Optional<CustomerEntity> customerJohn = customerJpaRepository.findByEmail("john@gmail.com");
        Assertions.assertTrue(customerJohn.isPresent());
        Assertions.assertEquals(customerJohn.get().getEmail(),"john@gmail.com");
    }
    @Test
    void findByUserNameWithOrdersTest() {
        Optional<CustomerEntity> customerJohn = customerJpaRepository.findByPhone("+48 670 204 936");
        Assertions.assertTrue(customerJohn.isPresent());
        Assertions.assertEquals(customerJohn.get().getPhone(),"+48 670 204 936");
    }
}
