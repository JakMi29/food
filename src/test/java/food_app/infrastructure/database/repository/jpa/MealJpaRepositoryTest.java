package food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.MealEntity;
import Food_app.infrastructure.database.entity.StreetEntity;
import Food_app.infrastructure.database.repository.jpa.MealJpaRepository;
import Food_app.infrastructure.database.repository.jpa.StreetJpaRepository;
import food_app.integration.configuration.PersistenceContainerTestConfiguration;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
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
public class MealJpaRepositoryTest {
    private MealJpaRepository mealJpaRepository;

    @Test
    void thatCarCanBeSavedCorrectly() {

        MealEntity meal= SomeFixtures.someMealEntity();

        mealJpaRepository.save(meal);
    }
}
