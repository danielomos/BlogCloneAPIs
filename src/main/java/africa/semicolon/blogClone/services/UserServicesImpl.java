package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.UserRepository;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.requests.UserLoginRequest;
import africa.semicolon.blogClone.dtos.responses.AddBlogResponse;
import africa.semicolon.blogClone.dtos.responses.LoginUserResponse;
import africa.semicolon.blogClone.dtos.responses.RegisterUserResponse;
import africa.semicolon.blogClone.exceptions.BlogCloneErrorException;
import africa.semicolon.blogClone.exceptions.UserAlreadyExit;
import africa.semicolon.blogClone.exceptions.WrongLoginDetails;
import africa.semicolon.blogClone.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BlogService blogService;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        validateUserAlreadyExists(request.getEmail());

        User user = new User();

        user.setUserName(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(String.format("%s successfully registered", request.getEmail()));
        return response;
    }

//    private void validateUserEmailRegister( <?> element) {
//        User savedUser = userRepository.findUserByUserName(element);
//        if(savedUser != null)
//            throw new UserAlreadyExit(String.format("User %s already exists", registerUserEmail));
//
//    }

    private void validateUserAlreadyExists(String registerUserEmail) {
        User savedUser = userRepository.findUserByUserName(registerUserEmail);
        if(savedUser != null)
            throw new UserAlreadyExit(String.format("User %s already exists", registerUserEmail));

    }

    @Override
    public LoginUserResponse userLogin(UserLoginRequest userLoginRequest) {



      User foundUser = validateUserNameFound(userLoginRequest.getEmail());

      if (foundUser.getPassword() != null && foundUser.getPassword().equals(userLoginRequest.getPassword())){

            LoginUserResponse loginMessage = new LoginUserResponse();
            loginMessage.setIsSuccessful(true);
            loginMessage.setMessage(String.format("%s successfully login", foundUser.getUserName()));
            return loginMessage ;
        }

            throw new WrongLoginDetails(String.format("Invalid username or password: %s", foundUser.getUserName()));

    }


    private User validateUserNameFound(String userName) {
        User savedUser = userRepository.findUserByUserName(userName);
        if (savedUser == null) {
            throw new BlogCloneErrorException(String.format("User %s not found", userName));

        }
    return  savedUser;
    }

    @Override
    public User getUserDetailsWithId(String userId){
        User foundUser = userRepository.findUserById(userId);
        return foundUser;
    }

    @Override
    public void save(User userToSave) {
        userRepository.save(userToSave);

    }
    @Override
    public AddBlogResponse createBlog(AddBlogRequest addBlogRequest) {
        User user = userRepository.findUserById(addBlogRequest.getUserId());
        if(user.getBlog().getId() != null){
            throw new BlogCloneErrorException(String.format("%s Already has a blog created ", user.getUserName()));
        }
        else{

            Blog inBlog = new Blog();

            Mapper.map(addBlogRequest, inBlog);
            Blog savedBlog = blogService.saveBlog(inBlog);

            user.setBlog(savedBlog);
            userRepository.save(user);
            AddBlogResponse addBlogResponse = new AddBlogResponse();
            addBlogResponse.setMessage((String.format("you have successfully created a new blog of %s",  inBlog.getBlogName())));
            return addBlogResponse;
        }



    }




}
