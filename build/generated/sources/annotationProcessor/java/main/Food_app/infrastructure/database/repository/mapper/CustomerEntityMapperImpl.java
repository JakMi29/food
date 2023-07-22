package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Address;
import Food_app.domain.Customer;
import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.CustomerEntity;
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
public class CustomerEntityMapperImpl implements CustomerEntityMapper {

    @Override
    public CustomerEntity map(Customer meal) {
        if ( meal == null ) {
            return null;
        }

        CustomerEntity.CustomerEntityBuilder customerEntity = CustomerEntity.builder();

        customerEntity.customerId( meal.getCustomerId() );
        customerEntity.name( meal.getName() );
        customerEntity.surname( meal.getSurname() );
        customerEntity.userName( meal.getUserName() );
        customerEntity.phone( meal.getPhone() );
        customerEntity.email( meal.getEmail() );
        customerEntity.user( userToUserEntity( meal.getUser() ) );
        customerEntity.address( addressToAddressEntity( meal.getAddress() ) );

        return customerEntity.build();
    }

    @Override
    public Customer map(CustomerEntity mealEntity) {
        if ( mealEntity == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.customerId( mealEntity.getCustomerId() );
        customer.name( mealEntity.getName() );
        customer.surname( mealEntity.getSurname() );
        customer.userName( mealEntity.getUserName() );
        customer.phone( mealEntity.getPhone() );
        customer.email( mealEntity.getEmail() );
        customer.address( addressEntityToAddress( mealEntity.getAddress() ) );
        customer.user( userEntityToUser( mealEntity.getUser() ) );

        return customer.build();
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
}
