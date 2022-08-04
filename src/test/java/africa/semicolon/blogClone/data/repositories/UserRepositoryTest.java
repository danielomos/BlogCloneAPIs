package africa.semicolon.blogClone.data.repositories;

import africa.semicolon.blogClone.data.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
   @Autowired
    private UserRepository userRepository;
    @Test
    public void testUserRepository()  {
        User user = new User();
       var savedUser = userRepository.save(user);
       assertNotNull(savedUser.getId());

    }


}