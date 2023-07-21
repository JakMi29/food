package food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.StreetEntity;
import Food_app.infrastructure.database.repository.jpa.StreetJpaRepository;
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
public class StreetJpaRepositoryTest {
    private StreetJpaRepository streetJpaRepository;

    @Test
    void saveStreetTest() {

        StreetEntity streetToSave= StreetEntity.builder()
                .name("testStreet")
                .build();

        streetJpaRepository.save(streetToSave);
        Optional<StreetEntity> street = streetJpaRepository.findByName("testStreet");
        Assertions.assertTrue(street.isPresent());
        Assertions.assertEquals(street.get().getName(),streetToSave.getName());

    }
}
