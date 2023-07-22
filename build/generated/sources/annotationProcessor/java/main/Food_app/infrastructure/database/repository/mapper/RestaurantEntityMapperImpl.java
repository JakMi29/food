package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Address;
import Food_app.domain.Restaurant;
import Food_app.domain.RestaurantOwner;
import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.RestaurantEntity;
import Food_app.infrastructure.database.entity.RestaurantOwnerEntity;
import Food_app.infrastructure.database.entity.StreetEntity;
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
public class RestaurantEntityMapperImpl implements RestaurantEntityMapper {

    @Override
    public Restaurant map(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        Restaurant.RestaurantBuilder restaurant = Restaurant.builder();

        restaurant.restaurantId( restaurantEntity.getRestaurantId() );
        restaurant.name( restaurantEntity.getName() );
        restaurant.foodCategory( restaurantEntity.getFoodCategory() );
        restaurant.phone( restaurantEntity.getPhone() );
        restaurant.email( restaurantEntity.getEmail() );
        restaurant.description( restaurantEntity.getDescription() );
        restaurant.address( addressEntityToAddress( restaurantEntity.getAddress() ) );
        restaurant.restaurantOwner( restaurantOwnerEntityToRestaurantOwner( restaurantEntity.getRestaurantOwner() ) );

        return restaurant.build();
    }

    @Override
    public RestaurantEntity map(Restaurant restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        RestaurantEntity.RestaurantEntityBuilder restaurantEntity1 = RestaurantEntity.builder();

        restaurantEntity1.restaurantId( restaurantEntity.getRestaurantId() );
        restaurantEntity1.name( restaurantEntity.getName() );
        restaurantEntity1.foodCategory( restaurantEntity.getFoodCategory() );
        restaurantEntity1.phone( restaurantEntity.getPhone() );
        restaurantEntity1.description( restaurantEntity.getDescription() );
        restaurantEntity1.email( restaurantEntity.getEmail() );
        restaurantEntity1.restaurantOwner( restaurantOwnerToRestaurantOwnerEntity( restaurantEntity.getRestaurantOwner() ) );
        restaurantEntity1.address( addressToAddressEntity( restaurantEntity.getAddress() ) );

        return restaurantEntity1.build();
    }

    protected Street streetEntityToStreet(StreetEntity streetEntity) {
        if ( streetEntity == null ) {
            return null;
        }

        Street.StreetBuilder street = Street.builder();

        street.streetId( streetEntity.getStreetId() );
        street.name( streetEntity.getName() );

        return street.build();
    }

    protected Address addressEntityToAddress(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.addressId( addressEntity.getAddressId() );
        address.country( addressEntity.getCountry() );
        address.city( addressEntity.getCity() );
        address.postalCode( addressEntity.getPostalCode() );
        address.street( streetEntityToStreet( addressEntity.getStreet() ) );
        address.houseNumber( addressEntity.getHouseNumber() );

        return address.build();
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

    protected RestaurantOwner restaurantOwnerEntityToRestaurantOwner(RestaurantOwnerEntity restaurantOwnerEntity) {
        if ( restaurantOwnerEntity == null ) {
            return null;
        }

        RestaurantOwner.RestaurantOwnerBuilder restaurantOwner = RestaurantOwner.builder();

        restaurantOwner.restaurantOwnerId( restaurantOwnerEntity.getRestaurantOwnerId() );
        restaurantOwner.name( restaurantOwnerEntity.getName() );
        restaurantOwner.surname( restaurantOwnerEntity.getSurname() );
        restaurantOwner.userName( restaurantOwnerEntity.getUserName() );
        restaurantOwner.phone( restaurantOwnerEntity.getPhone() );
        restaurantOwner.email( restaurantOwnerEntity.getEmail() );
        restaurantOwner.user( userEntityToUser( restaurantOwnerEntity.getUser() ) );

        return restaurantOwner.build();
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

    protected RestaurantOwnerEntity restaurantOwnerToRestaurantOwnerEntity(RestaurantOwner restaurantOwner) {
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

    protected StreetEntity streetToStreetEntity(Street street) {
        if ( street == null ) {
            return null;
        }

        StreetEntity.StreetEntityBuilder streetEntity = StreetEntity.builder();

        streetEntity.streetId( street.getStreetId() );
        streetEntity.name( street.getName() );

        return streetEntity.build();
    }

    protected AddressEntity addressToAddressEntity(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressEntity.AddressEntityBuilder addressEntity = AddressEntity.builder();

        addressEntity.addressId( address.getAddressId() );
        addressEntity.country( address.getCountry() );
        addressEntity.city( address.getCity() );
        addressEntity.postalCode( address.getPostalCode() );
        addressEntity.houseNumber( address.getHouseNumber() );
        addressEntity.street( streetToStreetEntity( address.getStreet() ) );

        return addressEntity.build();
    }
}
