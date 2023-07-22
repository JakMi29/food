package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.RestaurantStreet;
import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.RestaurantStreetEntity;
import Food_app.infrastructure.database.entity.StreetEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:39+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class RestaurantStreetEntityMapperImpl implements RestaurantStreetEntityMapper {

    @Override
    public RestaurantStreet map(RestaurantStreetEntity streetEntity) {
        if ( streetEntity == null ) {
            return null;
        }

        RestaurantStreet.RestaurantStreetBuilder restaurantStreet = RestaurantStreet.builder();

        restaurantStreet.restaurantStreetId( streetEntity.getRestaurantStreetId() );
        restaurantStreet.street( streetEntityToStreet( streetEntity.getStreet() ) );

        return restaurantStreet.build();
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
