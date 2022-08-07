package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.repositories.UserRepository;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.requests.UserLoginRequest;
import africa.semicolon.blogClone.dtos.responses.LoginUserResponse;
import africa.semicolon.blogClone.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void tearDown() {

    }

    @Test
    public void registerUser_repositorySizeIsOneTest(){
        RegisterUserResponse response = new RegisterUserResponse();
        RegisterUserRequest request = new RegisterUserRequest();
       request.setEmail("username ");
       request.setPassword("password");
        response= userService.registerUser(request);
        assertEquals(1L, userRepository.count());


    }
    @Test
    public void registerUserCanLogin(){
        RegisterUserResponse response = new RegisterUserResponse();
        RegisterUserRequest userRegisterrequest = new RegisterUserRequest();
        userRegisterrequest.setEmail("username");
        userRegisterrequest.setPassword("password");
        response= userService.registerUser(userRegisterrequest);

        assertEquals(1L, userRepository.count());

        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("username");
        userLoginRequest.setPassword("password");
        LoginUserResponse loginResponse = new LoginUserResponse();
        loginResponse =userService.userLogin(userLoginRequest);
        assertTrue(loginResponse.getIsSuccessful());

    }

}