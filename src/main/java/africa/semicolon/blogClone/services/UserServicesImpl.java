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
   private CommentService commentService;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        validateUserAlreadyExists(request.getEmail());

        User user = new User();

        user.setUserName(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(String.format("%s successfully registered", request.getEmail()));
        response.setUserId(user.getId());
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
            loginMessage.setUserId(foundUser.getId());
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
    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        users = userRepository.findAll();
        return users;
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
            addBlogResponse.setBlogId(savedBlog.getId());

            return addBlogResponse;
        }



    }

    private String convertUserEmailToID(String email) {
        User user = new User();
        user.setUserName(email);
       User userId = userRepository.findUserByUserName(user.getUserName());
//       System.out.println(email );
//       System.out.println(user.getUserName() + user.getId());

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
        addAArticleResponse.setArticleId(savedArticle.getId());

        return addAArticleResponse;

    }


    @Override
    public AppUserArticleResponse getUserAllArticlesList(String email) {
//        String userId = convertUserEmailToID(email);

//        User user = getUserDetailsWithId(userId);
        User user = userRepository.findUserByUserName(email);
        if (articleService.getArticleCount(user) == 0) {
            throw new BlogCloneErrorException(String.format("%s currently has no articles", user.getUserName()));
        }


//        Blog blog = getUserBlogDetails(user.getId());
        Blog blog = blogService.getBlogById(user.getBlog().getId());
        List<Article> userBlogArticles = blog.getArticles();
//        System.out.println(userBlogArticles.size());
        AppUserArticleResponse appUserArticleResponse = new AppUserArticleResponse();
        appUserArticleResponse.setArticles(userBlogArticles);
//        System.out.println(appUserArticleResponse.getArticles().size());



//        List<Article> userBlogArticles = blogService.getArticlesInaBlog(blog);
//        List<UserArticleListResponse> response = new ArrayList<UserArticleListResponse>();
//
//        for(Article article : userBlogArticles) {
//            UserArticleListResponse eachResponse = new UserArticleListResponse();
//            Mapper.map(article, eachResponse);
//            response.add(eachResponse);
//
//        }
//        AppUserArticleResponse appUserArticleResponse = new AppUserArticleResponse();
//        appUserArticleResponse.setArticles(response);


        return appUserArticleResponse;
    }

    @Override
    public void clearDatabases() {
        userRepository.deleteAll();
    }

    @Override
    public SingleUserArticleResponse getArticleInUserBlog(String id) {

          Article article = articleService.getArticleInDb(id);
          SingleUserArticleResponse articleResponse = new SingleUserArticleResponse();
          Mapper.map(article, articleResponse);
        return articleResponse;

    }

    @Override
    public DeleteArticleResponse deleteArticleInUserBlog(DeleteArticleRequest request) {

//        System.out.println(blogId);
        Blog blog = blogService.getBlogById(request.getBlogId());
//        System.out.println(blog.getId());
//        System.out.println(title);
        String articleId = blogService.getArticle(request.getBlogId(),request.getArticleTitle());
//    System.out.println(articleId);
        Article article = articleService.findArticleById(articleId);
//        System.out.println(articleService.count());
//        System.out.println(article.getId());
//        System.out.println(article.getTitle());
//        List<Article> articles =blog.getArticles();
//       articles.remove(article);
//        blogService.saveBlog(blog);
//        articleService.deleteArticleInaBlog(articleId);
 List<Article> listAllUserBlogArticles = blog.getArticles();
        articleService.deleteArticleInaBlog(articleId);

//        System.out.println(listAllUserBlogArticles.size());
 listAllUserBlogArticles.remove(article);
// System.out.println(listAllUserBlogArticles.size());
 blog.setArticles(listAllUserBlogArticles);
 blogService.saveBlog(blog);

// System.out.println(blog.getArticles().size());



//
//        List<Article> articles = blog.getArticles();
//        for (Article article : articles) {
//           if( article.getTitle().equalsIgnoreCase(title)) {
////                articles.remove(article);
////              blog.setArticles(articles);
////              blogService.saveBlog(blog);
//
//                Article foundarticle = article;
//               articleService.deleteArticleInaBlog(article.get.Id());
//           }
//        }


////        articleService.deleteArticleInaBlog(title);
//        blogService.deleteArticleInaBlog(title);
        DeleteArticleResponse deleteArticleResponse = new DeleteArticleResponse();
        deleteArticleResponse.setMassage(String.format("%s successfully deleted", request.getArticleTitle()));
        deleteArticleResponse.setCount(blog.getArticles().size());
        return  deleteArticleResponse;
    }

    @Override
    public AddCommentResponse AddCommitToArticle(AddCommentRequest addCommitRequest) {
        Comments comment = commentService.addCommit(addCommitRequest);
        AddCommentResponse addCommentResponse = new AddCommentResponse();

        Mapper.map(addCommentResponse, comment);
        return addCommentResponse;
    }

    public Blog getUserBlogDetails(String userId) {
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
