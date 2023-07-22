package Food_app.api.dto.mapper;

import Food_app.api.dto.StreetDTO;
import Food_app.domain.Street;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-22T14:43:39+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class StreetMapperImpl implements StreetMapper {

    @Override
    public Street map(StreetDTO street) {
        if ( street == null ) {
            return null;
        }

        Street.StreetBuilder street1 = Street.builder();

        street1.name( street.getName() );

        return street1.build();
    }
}
