package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Address;
import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.AddressEntity;
import Food_app.infrastructure.database.entity.StreetEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class AddressEntityMapperImpl implements AddressEntityMapper {

    @Override
    public AddressEntity map(Address address) {
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

    @Override
    public Address map(AddressEntity addressEntity) {
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

    protected StreetEntity streetToStreetEntity(Street street) {
        if ( street == null ) {
            return null;
        }

        StreetEntity.StreetEntityBuilder streetEntity = StreetEntity.builder();

        streetEntity.streetId( street.getStreetId() );
        streetEntity.name( street.getName() );

        return streetEntity.build();
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
}
