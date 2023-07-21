package Food_app.infrastructure.database.repository.jpa;

import Food_app.infrastructure.database.entity.RestaurantOwnerEntity;
import Food_app.infrastructure.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantOwnerJpaRepository extends JpaRepository<RestaurantOwnerEntity, Integer> {

    Optional<RestaurantOwnerEntity> findByEmail(String email);

    Optional<RestaurantOwnerEntity> findByUserName(String userName);

    Optional<RestaurantOwnerEntity> findByPhone(String phone);

    RestaurantOwnerEntity findByUser(UserEntity user);
}
