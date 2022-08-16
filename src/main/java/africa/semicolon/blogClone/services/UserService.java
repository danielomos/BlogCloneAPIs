package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.dtos.requests.*;
import africa.semicolon.blogClone.dtos.responses.*;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginUserResponse userLogin(UserLoginRequest userLoginRequest);

     User getUserDetailsWithId(String userId);
    public List<User> getAllUser();


    void save(User userToSave);

    AddBlogResponse createBlog(AddBlogRequest addBlogRequest);

    AddAArticleResponse createArticle(AddArticleRequest addAArticleRequest);

    AppUserArticleResponse getUserAllArticlesList(String userId );
    public void clearDatabases();

    public SingleUserArticleResponse getArticleInUserBlog(String articleId);
    public DeleteArticleResponse deleteArticleInUserBlog(String title);

     public AddCommentResponse AddCommitToArticle(AddCommentRequest addCommitRequest);

    public Blog getUserBlogDetails(String userId);



}
