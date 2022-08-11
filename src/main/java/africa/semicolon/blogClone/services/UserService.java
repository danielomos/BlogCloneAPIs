package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.dtos.requests.*;
import africa.semicolon.blogClone.dtos.responses.*;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginUserResponse userLogin(UserLoginRequest userLoginRequest);

     User getUserDetailsWithId(String userId);


    void save(User userToSave);

    AddBlogResponse createBlog(AddBlogRequest addBlogRequest);

    AddAArticleResponse createArticle(AddArticleRequest addAArticleRequest);

    AppUserArticleResponse getUserAllArticlesList(String userId );
    public void clearDatabases();

    public SingleUserArticleResponse getArticleInaBlog(String articleId);
     public DeleteArticleResponse deleteArticleInaBlog(String title);

     public AddCommentResponse AddCommitToArticle(AddCommentRequest addCommitRequest);
}
