package Food_app.infrastructure.database.repository;

import Food_app.domain.Street;
import Food_app.infrastructure.database.repository.jpa.StreetJpaRepository;
import Food_app.infrastructure.database.repository.mapper.StreetEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class StreetRepository {
    private final StreetJpaRepository streetJpaRepository;
    private final StreetEntityMapper streetEntityMapper;

    public void createStreet(Street street) {
        streetJpaRepository.save(streetEntityMapper.map(street));
    }

    public Optional<Street> findStreetByName(String name) {
        return streetJpaRepository.findByName(name).map(streetEntityMapper::map);
    }
}
