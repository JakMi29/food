package Food_app.business;

import Food_app.api.dto.CreateRestaurantOwnerDTO;
import Food_app.business.dao.RestaurantOwnerDAO;
import Food_app.domain.Address;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantOwner;
import Food_app.domain.Street;
import Food_app.domain.exception.UserAlreadyExist;
import Food_app.infrastructure.security.RoleEntity;
import Food_app.infrastructure.security.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@AllArgsConstructor
public class RestaurantOwnerService {
    private final RestaurantOwnerDAO restaurantOwnerDAO;
    private final StreetService streetService;
    private final RestaurantService restaurantService;

    @Transactional
    public RestaurantOwner findByUser(User user) {
        return restaurantOwnerDAO.findByUser(user);
    }

    @Transactional
    public void createRestaurantOwner(CreateRestaurantOwnerDTO createRestaurantOwnerDTO) {
        checkIfCustomerWithUniqueVariablesExist(createRestaurantOwnerDTO);

        Restaurant restaurant = Restaurant.builder()
                .name(createRestaurantOwnerDTO.getRestaurantName())
                .email(createRestaurantOwnerDTO.getRestaurantEmail())
                .phone(createRestaurantOwnerDTO.getRestaurantPhone())
                .foodCategory(createRestaurantOwnerDTO.getFoodCategory())
                .address(addressBuilder(createRestaurantOwnerDTO))
                .restaurantOwner(restaurantOwnerBuilder(createRestaurantOwnerDTO))
                .build();
        restaurantService.createRestaurant(restaurant);
        log.info("Successful created new restaurant owner: [%s]".formatted(createRestaurantOwnerDTO.getName()));
    }

    @Transactional
    void checkIfCustomerWithUniqueVariablesExist(CreateRestaurantOwnerDTO restaurantOwner) {
        if (restaurantOwnerDAO.findByEmail(restaurantOwner.getEmail()).isPresent()) {
            log.error("User with this email already exist");
            throw new UserAlreadyExist("User with this email already exist");
        } else if (restaurantOwnerDAO.findByPhone(restaurantOwner.getPhone()).isPresent()) {
            log.error("User with this phone already exist");
            throw new UserAlreadyExist("User with this phone already exist");
        } else if (restaurantOwnerDAO.findByUserName(restaurantOwner.getUserName()).isPresent()) {
            log.error("User with this user name already exist");
            throw new UserAlreadyExist("User with this user name already exist");
        }
    }

    private Address addressBuilder(CreateRestaurantOwnerDTO createRestaurantOwnerDTO) {
        Street street = streetService.findOrCreateStreet(createRestaurantOwnerDTO.getStreet());
        return Address.builder()
                .city(createRestaurantOwnerDTO.getCity())
                .country(createRestaurantOwnerDTO.getCountry())
                .postalCode(createRestaurantOwnerDTO.getPostalCode())
                .street(street)
                .houseNumber(createRestaurantOwnerDTO.getHomeNumber())
                .build();
    }

    private RestaurantOwner restaurantOwnerBuilder(CreateRestaurantOwnerDTO createRestaurantOwnerDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return RestaurantOwner.builder()
                .name(createRestaurantOwnerDTO.getName())
                .surname(createRestaurantOwnerDTO.getSurname())
                .userName(createRestaurantOwnerDTO.getUserName())
                .email(createRestaurantOwnerDTO.getEmail())
                .phone(createRestaurantOwnerDTO.getPhone())
                .user(
                        User.builder()
                                .userName(createRestaurantOwnerDTO.getUserName())
                                .email(createRestaurantOwnerDTO.getEmail())
                                .password(passwordEncoder.encode(createRestaurantOwnerDTO.getPassword()))
                                .active(true)
                                .role(RoleEntity.builder().id(2).build())
                                .build()
                )
                .build();
    }

}
