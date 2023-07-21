package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.CustomerEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query("SELECT r FROM CustomerEntity r JOIN FETCH r.orders o WHERE r.userName = :name")
    Optional<CustomerEntity> findWithOrdersByUserName(@NonNull String name);

    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByUserName(String userName);

    Optional<CustomerEntity> findByPhone(String phone);
}
