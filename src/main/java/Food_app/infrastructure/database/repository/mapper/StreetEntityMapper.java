package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Street;
import Food_app.infrastructure.database.entity.StreetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StreetEntityMapper {

    StreetEntity map(Street street);

    Street map(StreetEntity streetEntity);


}
