package de.hs.da.hskleinanzeigen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hs.da.hskleinanzeigen.domain.User;
import de.hs.da.hskleinanzeigen.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
public class RedisCacheTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @MockBean
    UserService userService;

    @Test
    void getUser_returnCachedUser() throws JsonProcessingException {
        stringRedisTemplate.opsForValue().set("Users:100",
                "{\"id\":\"100\",\"email\":\"ouassime@gmail.com\",\"password\":\"123456\",\"firstName\":\"Ouassime\",\"lastName\":\"Boulmani\",\"phone\":\"17645738494\",\"location\":\"Darmstadt\"}");

        String stringUser = stringRedisTemplate.opsForValue().get("Users:100");
        //Convert user json to user object
        User redisUser = new ObjectMapper().readValue(stringUser, User.class);

        when(userService.findUserById(100)).thenReturn(Optional.of(new User(100, "ouassime@gmail.com", "123456", "Ouassime", "Boulmani", "17645738494", "Darmstadt", null)));
        Optional<User> mockUser = userService.findUserById(100);

        assertEquals(redisUser, mockUser.get());
    }
}