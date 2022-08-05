package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.UserRepository;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.requests.UserLoginRequest;
import africa.semicolon.blogClone.dtos.responses.LoginUserResponse;
import africa.semicolon.blogClone.dtos.responses.RegisterUserResponse;
import africa.semicolon.blogClone.exceptions.BlogCloneErrorException;
import africa.semicolon.blogClone.exceptions.WrongLoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserService{

    @Autowired
    private UserRepository userRepository;






    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = new User();
        user.setUserName(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(String.format("%s successfully registered", request.getEmail()));
        return response;
    }

    @Override
    public LoginUserResponse userLogin(UserLoginRequest userLoginRequest) {



      User foundUser = validateUserEmail(userLoginRequest.getEmail());

      if (foundUser.getPassword() != null && foundUser.getPassword().equals(userLoginRequest.getPassword())){

            LoginUserResponse loginMessage = new LoginUserResponse();
            loginMessage.setIsSuccessful(true);
            loginMessage.setMessage(String.format("%s successfully login", foundUser.getUserName()));
            return loginMessage ;
        }

            throw new WrongLoginDetails(String.format("Invalid username or password: %s", foundUser.getUserName()));

    }


    private User validateUserEmail(String userName) {
        User savedUser = userRepository.findUserByUserName(userName);
        if (savedUser == null) {
            throw new BlogCloneErrorException(String.format("User %s not found", userName));

        }
    return  savedUser;
    }





}
