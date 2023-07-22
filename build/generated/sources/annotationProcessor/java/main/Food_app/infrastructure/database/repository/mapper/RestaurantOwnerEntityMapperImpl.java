package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.RestaurantOwner;
import Food_app.infrastructure.database.entity.RestaurantOwnerEntity;
import Food_app.infrastructure.security.User;
import Food_app.infrastructure.security.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:39+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class RestaurantOwnerEntityMapperImpl implements RestaurantOwnerEntityMapper {

    @Override
    public RestaurantOwner map(RestaurantOwnerEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        RestaurantOwner.RestaurantOwnerBuilder restaurantOwner = RestaurantOwner.builder();

        restaurantOwner.restaurantOwnerId( restaurantEntity.getRestaurantOwnerId() );
        restaurantOwner.name( restaurantEntity.getName() );
        restaurantOwner.surname( restaurantEntity.getSurname() );
        restaurantOwner.userName( restaurantEntity.getUserName() );
        restaurantOwner.phone( restaurantEntity.getPhone() );
        restaurantOwner.email( restaurantEntity.getEmail() );
        restaurantOwner.user( userEntityToUser( restaurantEntity.getUser() ) );

        return restaurantOwner.build();
    }

    @Override
    public RestaurantOwnerEntity map(RestaurantOwner restaurantOwner) {
        if ( restaurantOwner == null ) {
            return null;
        }

        RestaurantOwnerEntity.RestaurantOwnerEntityBuilder restaurantOwnerEntity = RestaurantOwnerEntity.builder();

        restaurantOwnerEntity.restaurantOwnerId( restaurantOwner.getRestaurantOwnerId() );
        restaurantOwnerEntity.name( restaurantOwner.getName() );
        restaurantOwnerEntity.surname( restaurantOwner.getSurname() );
        restaurantOwnerEntity.phone( restaurantOwner.getPhone() );
        restaurantOwnerEntity.userName( restaurantOwner.getUserName() );
        restaurantOwnerEntity.email( restaurantOwner.getEmail() );
        restaurantOwnerEntity.user( userToUserEntity( restaurantOwner.getUser() ) );

        return restaurantOwnerEntity.build();
    }

    protected User userEntityToUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userEntity.getId() );
        user.userName( userEntity.getUserName() );
        user.email( userEntity.getEmail() );
        user.password( userEntity.getPassword() );
        user.active( userEntity.getActive() );
        user.role( userEntity.getRole() );

        return user.build();
    }

    protected UserEntity userToUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( user.getId() );
        userEntity.userName( user.getUserName() );
        userEntity.email( user.getEmail() );
        userEntity.password( user.getPassword() );
        userEntity.active( user.getActive() );
        userEntity.role( user.getRole() );

        return userEntity.build();
    }
}
