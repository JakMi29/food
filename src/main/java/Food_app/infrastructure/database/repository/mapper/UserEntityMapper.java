package Food_app.infrastructure.database.repository.mapper;

import Food_app.infrastructure.security.User;
import Food_app.infrastructure.security.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserEntity map(User user);

    User map(UserEntity userEntity);
}
