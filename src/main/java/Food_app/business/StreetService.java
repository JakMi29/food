package Food_app.business;

import Food_app.domain.Street;
import Food_app.infrastructure.database.repository.StreetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StreetService {
    private final StreetRepository streetRepository;


    @Transactional
    public void createStreet(String streetName) {
        Street street = Street.builder().name(streetName).build();
        streetRepository.createStreet(street);
    }

    @Transactional
    public Optional<Street> findStreet(String streetName) {
        return streetRepository.findStreetByName(streetName);
    }

    @Transactional
    public Street findOrCreateStreet(String streetName) {
        Optional<Street> street = findStreet(streetName);
        if (street.isEmpty()) {
            createStreet(streetName);
            return findStreet(streetName).orElseThrow();
        } else
            return street.get();
    }


}
