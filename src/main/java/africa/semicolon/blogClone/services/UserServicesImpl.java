package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.models.Comments;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.UserRepository;
import africa.semicolon.blogClone.dtos.requests.*;
import africa.semicolon.blogClone.dtos.responses.*;
import africa.semicolon.blogClone.exceptions.BlogCloneErrorException;
import africa.semicolon.blogClone.exceptions.BlogNotFoundException;
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
    private ArticleService articleService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogService blogService;

    @Autowired
    CommentService commentService;

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
            loginMessage.setEmail(foundUser.getUserName());
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
        String userId = convertUserEmailToID(addBlogRequest.getEmail());
        User user = userRepository.findUserById(userId);
        if(user.getBlog() != null){
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

    private String convertUserEmailToID(String email) {
        User user = new User();
        user.setUserName(email);
       User userId = userRepository.findUserByUserName(user.getUserName());
        return userId.getId();

    }

    @Override
    public AddAArticleResponse createArticle(AddArticleRequest addArticleRequest) {
        String userId = convertUserEmailToID(addArticleRequest.getEmail());
        Blog userBlog = getUserBlogDetails(userId);

        Article newArticle = new Article();
        Mapper.map(newArticle, addArticleRequest);
        Article savedArticle = articleService.saveArticle(newArticle);
        userBlog.getArticles().add(savedArticle);
        blogService.saveBlog(userBlog);
        AddAArticleResponse addAArticleResponse = new AddAArticleResponse();
        addAArticleResponse.setMessage("Article saved successfully");
        return addAArticleResponse;

    }
    public User getUserId(String userId) {
        User user = userRepository.findUserById(userId);
        return user;
    }

    @Override
    public AppUserArticleResponse getUserAllArticlesList(String email) {
        String userId = convertUserEmailToID(email);
        User user = getUserId(userId);
        if (articleService.getArticleCount(user) == 0) {
            throw new BlogCloneErrorException(String.format("%s currently has no articles", user.getUserName()));
        }
        Blog blog = getUserBlogDetails(userId);

        List<Article> userBlogArticles = blogService.getArticlesInaBlog(blog);
        List<UserArticleListResponse> response = new ArrayList<UserArticleListResponse>();

        for(Article article : userBlogArticles) {
            UserArticleListResponse eachResponse = new UserArticleListResponse();
            Mapper.map(article, eachResponse);
            response.add(eachResponse);

        }
        AppUserArticleResponse appUserArticleResponse = new AppUserArticleResponse();
        appUserArticleResponse.setArticles(response);


        return appUserArticleResponse;
    }

    @Override
    public void clearDatabases() {
        userRepository.deleteAll();
    }

    @Override
    public SingleUserArticleResponse getArticleInaBlog(String title) {

          Article article = articleService.getArticleInDb(title);
          SingleUserArticleResponse articleResponse = new SingleUserArticleResponse();
          Mapper.map(article, articleResponse);
        return articleResponse;

    }

    @Override
    public DeleteArticleResponse deleteArticleInaBlog(String title) {
        articleService.deleteArticleInaBlog(title);
        DeleteArticleResponse deleteArticleResponse = new DeleteArticleResponse();
        deleteArticleResponse.setMassage(String.format("%s successfully deleted", title));
        return  deleteArticleResponse;
    }

    @Override
    public AddCommentResponse AddCommitToArticle(AddCommentRequest addCommitRequest) {
        Comments comment = commentService.addCommit(addCommitRequest);
        AddCommentResponse addCommentResponse = new AddCommentResponse();

        Mapper.map(addCommentResponse, comment);
        return addCommentResponse;
    }

    private Blog getUserBlogDetails(String userId) {
        User user = userRepository.findUserById(userId);
        Blog userBlog = user.getBlog();
//        Blog userBlog = blogService.getUserBlog(blog);
        if (userBlog != null) {
            return userBlog;

        }
        throw new BlogNotFoundException("User does not have a blog");
    }



    @Override
    public String toString() {
        return "UserServicesImpl{" +
                "articleService=" + articleService +
                ", userRepository=" + userRepository +
                ", blogService=" + blogService +
                '}';
    }

}
