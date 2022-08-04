package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.UserRepository;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.responses.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
