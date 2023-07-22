package Food_app.infrastructure.database.repository.mapper;

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
public class UserEntityMapperImpl implements UserEntityMapper {

    @Override
    public UserEntity map(User user) {
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

    @Override
    public User map(UserEntity userEntity) {
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
