package food_app.infrastructure.database.repository.mapper;

import Food_app.api.dto.mapper.StreetMapper;
import Food_app.infrastructure.database.repository.mapper.UserEntityMapper;
import Food_app.infrastructure.security.User;
import Food_app.infrastructure.security.UserEntity;
import food_app.util.SomeFixtures;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserEntityMapperTest {

    private UserEntityMapper userEntityMapper;
    @BeforeEach
    public void setUp() {
        userEntityMapper = Mappers.getMapper(UserEntityMapper.class);
    }
    @Test
    void testMapUserToUserEntity() {
        // given
        User user = SomeFixtures.someUser();

        // when
        UserEntity userEntity = userEntityMapper.map(user);

        // then
        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getUserName(), userEntity.getUserName());
        assertEquals(user.getEmail(), userEntity.getEmail());
        assertEquals(user.getActive(), userEntity.getActive());
        assertEquals(user.getPassword(), userEntity.getPassword());
        assertEquals(user.getRole().getId(), userEntity.getRole().getId());
        assertEquals(user.getRole().getRole(), userEntity.getRole().getRole());
    }

    @Test
    void testMapUserEntityToUser() {
        // given
        UserEntity userEntity = SomeFixtures.someUserEntity();

        // when
        User user = userEntityMapper.map(userEntity);

        // then
        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getUserName(), user.getUserName());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getActive(), user.getActive());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getRole().getId(), user.getRole().getId());
        assertEquals(userEntity.getRole().getRole(), user.getRole().getRole());
    }
}
