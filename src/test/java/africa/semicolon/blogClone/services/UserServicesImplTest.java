package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.repositories.UserRepository;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;



    @Test
    public void registerUser_repositorySizeIsOneTest(){
        RegisterUserResponse response = new RegisterUserResponse();
        RegisterUserRequest request = new RegisterUserRequest();
       request.setEmail("username ");
       request.setPassword("password");
        response= userService.registerUser(request);
        assertEquals(1L, userRepository.count());


    }

}