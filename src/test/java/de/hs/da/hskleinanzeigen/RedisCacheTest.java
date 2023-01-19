package de.hs.da.hskleinanzeigen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hs.da.hskleinanzeigen.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class RedisCacheTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void getUser_returnCachedUser() throws JsonProcessingException {
        stringRedisTemplate.opsForValue().set("Users:100",
                "{\"id\":\"100\",\"email\":\"ouassime@gmail.com\",\"password\":\"123456\",\"firstName\":\"Ouassime\",\"lastName\":\"Boulmani\",\"phone\":\"17645738494\",\"location\":\"Darmstadt\"}");

        String stringUser = stringRedisTemplate.opsForValue().get("Users:100");
        //Convert json user to user object
        User user = new ObjectMapper().readValue(stringUser, User.class);
        assertEquals(100, user.getId());
    }
}
