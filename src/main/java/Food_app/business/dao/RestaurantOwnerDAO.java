package Food_app.business.dao;

import Food_app.domain.RestaurantOwner;
import Food_app.infrastructure.security.User;

import java.util.Optional;

public interface RestaurantOwnerDAO {

    Optional<RestaurantOwner> findByEmail(String email);

    Optional<RestaurantOwner> findByPhone(String phone);

    Optional<RestaurantOwner> findByUserName(String userName);


    RestaurantOwner findByUser(User user);
}
