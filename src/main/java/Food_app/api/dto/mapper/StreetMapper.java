package Food_app.api.dto.mapper;

import Food_app.api.dto.StreetDTO;
import Food_app.domain.Street;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StreetMapper extends BigDecimalMapper {

    Street map(final StreetDTO street);
}
