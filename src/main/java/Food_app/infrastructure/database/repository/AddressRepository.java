package Food_app.infrastructure.database.repository;

import Food_app.infrastructure.database.repository.jpa.AddressJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AddressRepository {
    private final AddressJpaRepository addressJpaRepository;
}
