package food_app.bussines;

import Food_app.api.dto.CreateRestaurantOwnerDTO;
import Food_app.business.RestaurantOwnerService;
import Food_app.business.RestaurantService;
import Food_app.business.StreetService;
import Food_app.business.dao.RestaurantOwnerDAO;
import Food_app.domain.RestaurantOwner;
import Food_app.domain.exception.UserAlreadyExist;
import food_app.util.SomeFixtures;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantOwnerServiceTest {

    @InjectMocks
    private RestaurantOwnerService restaurantOwnerService;
    @Mock
    private RestaurantOwnerDAO restaurantOwnerDAO;
    @Mock
    private StreetService streetService;
    @Mock
    private RestaurantService restaurantService;

    @Test
    public void createRestaurantOwner() {
        CreateRestaurantOwnerDTO createRestaurantOwnerDTO = SomeFixtures.someCreateRestaurantOwner();
        RestaurantOwner restaurantOwner = SomeFixtures.someRestaurantOwner();

        when(restaurantOwnerDAO.findByEmail(anyString()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(restaurantOwner))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.empty());

        when(restaurantOwnerDAO.findByPhone(anyString()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(restaurantOwner))
                .thenReturn(Optional.empty());
        when(restaurantOwnerDAO.findByUserName(anyString()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(restaurantOwner));

        restaurantOwnerService.createRestaurantOwner(createRestaurantOwnerDTO);

        Assertions.assertThrows(UserAlreadyExist.class, () -> restaurantOwnerService.createRestaurantOwner(createRestaurantOwnerDTO));
        Assertions.assertThrows(UserAlreadyExist.class, () -> restaurantOwnerService.createRestaurantOwner(createRestaurantOwnerDTO));
        Assertions.assertThrows(UserAlreadyExist.class, () -> restaurantOwnerService.createRestaurantOwner(createRestaurantOwnerDTO));


        verify(restaurantOwnerDAO, times(4)).findByEmail(createRestaurantOwnerDTO.getEmail());
        verify(restaurantOwnerDAO, times(3)).findByPhone(createRestaurantOwnerDTO.getPhone());
        verify(restaurantOwnerDAO, times(2)).findByUserName(createRestaurantOwnerDTO.getUserName());
    }
}
