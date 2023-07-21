package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.StreetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreetJpaRepository extends JpaRepository<StreetEntity, Integer> {
    Optional<StreetEntity> findByName(String name);
}
