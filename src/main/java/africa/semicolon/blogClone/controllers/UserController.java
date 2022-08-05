package africa.semicolon.blogClone.controllers;

import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.requests.UserLoginRequest;
import africa.semicolon.blogClone.dtos.responses.LoginUserResponse;
import africa.semicolon.blogClone.dtos.responses.RegisterUserResponse;
import africa.semicolon.blogClone.exceptions.WrongLoginDetails;
import africa.semicolon.blogClone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

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
    @GetMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginRequest  userLoginRequest){
        try{
            LoginUserResponse loginUserResponse = userService.userLogin(userLoginRequest);

            return new ResponseEntity<>(loginUserResponse.getMessage(), HttpStatus.OK);
        }
        catch (WrongLoginDetails err) {
            return new ResponseEntity<>(err.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
