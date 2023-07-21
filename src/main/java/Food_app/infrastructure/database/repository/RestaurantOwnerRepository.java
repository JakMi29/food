package Food_app.infrastructure.database.repository;

import Food_app.business.dao.RestaurantOwnerDAO;
import Food_app.domain.RestaurantOwner;
import Food_app.infrastructure.database.repository.jpa.RestaurantOwnerJpaRepository;
import Food_app.infrastructure.database.repository.mapper.RestaurantOwnerEntityMapper;
import Food_app.infrastructure.database.repository.mapper.UserEntityMapper;
import Food_app.infrastructure.security.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantOwnerRepository implements RestaurantOwnerDAO {
    private final RestaurantOwnerJpaRepository restaurantOwnerJpaRepository;
    private final RestaurantOwnerEntityMapper restaurantOwnerEntityMapper;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<RestaurantOwner> findByEmail(String email) {
        return restaurantOwnerJpaRepository.findByEmail(email).map(restaurantOwnerEntityMapper::map);
    }

    @Override
    public Optional<RestaurantOwner> findByUserName(String userName) {
        return restaurantOwnerJpaRepository.findByUserName(userName).map(restaurantOwnerEntityMapper::map);
    }

    @Override
    public Optional<RestaurantOwner> findByPhone(String phone) {
        return restaurantOwnerJpaRepository.findByPhone(phone).map(restaurantOwnerEntityMapper::map);
    }

    @Override
    public RestaurantOwner findByUser(User user) {
        return restaurantOwnerEntityMapper.map(restaurantOwnerJpaRepository.findByUser(userEntityMapper.map(user)));
    }
}
