package Food_app.infrastructure.database.repository.mapper;

import Food_app.domain.Address;
import Food_app.infrastructure.database.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressEntityMapper {

    AddressEntity map(Address address);

    Address map(AddressEntity addressEntity);
}
