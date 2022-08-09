package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.dtos.requests.AddArticleRequest;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.requests.UserLoginRequest;
import africa.semicolon.blogClone.dtos.responses.*;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginUserResponse userLogin(UserLoginRequest userLoginRequest);

     User getUserDetailsWithId(String userId);


    void save(User userToSave);

    AddBlogResponse createBlog(AddBlogRequest addBlogRequest);

    AddAArticleResponse createArticle(AddArticleRequest addAArticleRequest);

    AppUserArticleResponse getUserAllArticlesList(String userId );
    public void clearDatabases();

    public SingleUserArticleResponse getArticle(String articleId);
    
}
