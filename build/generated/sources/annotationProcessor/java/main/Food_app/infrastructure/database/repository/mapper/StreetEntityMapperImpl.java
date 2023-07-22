package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.StreetEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:38+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class StreetEntityMapperImpl implements StreetEntityMapper {

    @Override
    public StreetEntity map(Street street) {
        if ( street == null ) {
            return null;
        }

        StreetEntity.StreetEntityBuilder streetEntity = StreetEntity.builder();

        streetEntity.streetId( street.getStreetId() );
        streetEntity.name( street.getName() );

        return streetEntity.build();
    }

    @Override
    public Street map(StreetEntity streetEntity) {
        if ( streetEntity == null ) {
            return null;
        }

        Street.StreetBuilder street = Street.builder();

        street.streetId( streetEntity.getStreetId() );
        street.name( streetEntity.getName() );

        return street.build();
    }
}
