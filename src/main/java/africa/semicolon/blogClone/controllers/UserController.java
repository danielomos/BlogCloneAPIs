package africa.semicolon.blogClone.controllers;

import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.requests.UserLoginRequest;
import africa.semicolon.blogClone.dtos.responses.AddBlogResponse;
import africa.semicolon.blogClone.dtos.responses.LoginUserResponse;
import africa.semicolon.blogClone.dtos.responses.RegisterUserResponse;
import africa.semicolon.blogClone.exceptions.BlogCloneErrorException;
import africa.semicolon.blogClone.exceptions.BlogNotCreatedException;
import africa.semicolon.blogClone.exceptions.WrongLoginDetails;
import africa.semicolon.blogClone.services.BlogService;
import africa.semicolon.blogClone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        try{
            RegisterUserResponse response = userService.registerUser(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/user/auth")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginRequest  userLoginRequest){
        try{
            LoginUserResponse loginUserResponse = userService.userLogin(userLoginRequest);

            return new ResponseEntity<>(loginUserResponse.getMessage(), HttpStatus.OK);
        }
        catch (WrongLoginDetails err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @PatchMapping("/blog/create")
    public ResponseEntity<?> CreateBlog(@RequestBody AddBlogRequest addBlogRequest){
        try{
            AddBlogResponse addBlogResponse = userService.createBlog(addBlogRequest);
            return new ResponseEntity<>(addBlogResponse.getMessage(), HttpStatus.CREATED);
        }
        catch (BlogCloneErrorException err){
            return  new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
