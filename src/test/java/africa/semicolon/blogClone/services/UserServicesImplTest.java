package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.repositories.UserRepository;
import africa.semicolon.blogClone.dtos.requests.AddArticleRequest;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogClone.dtos.requests.UserLoginRequest;
import africa.semicolon.blogClone.dtos.responses.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServicesImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private RegisterUserRequest request;
    private RegisterUserResponse response;
    private UserLoginRequest userLoginRequest;
    private LoginUserResponse   loginUserResponse;

    private AddBlogRequest addBlogRequest;

    private AddBlogResponse addBlogResponse;

    private AddArticleRequest   addArticleRequest;
    private AddAArticleResponse addArticleResponse;


    @BeforeEach
    void setUP() {
     request = new RegisterUserRequest();

     response = new RegisterUserResponse();
     userLoginRequest = new UserLoginRequest();
     loginUserResponse = new LoginUserResponse();
     addBlogRequest = new AddBlogRequest();
     addBlogResponse = new AddBlogResponse();
     addArticleRequest = new AddArticleRequest();
     addArticleResponse = new AddAArticleResponse();
    }
    @AfterEach
    void tearDown() {
        userService.clearDatabases();


    }

    @Test
    public void registerUser_repositorySizeIsOneTest(){
       request.setEmail("username");
       request.setPassword("password");
       response= userService.registerUser(request);
//       assertNotNull(userRepository.count());
       assertEquals(1, userRepository.count());

    }
    @Test
    public void registerUserCanLogin(){
        request.setEmail("username1");
        request.setPassword("password");
        response= userService.registerUser(request);
        assertEquals(1, userRepository.count());


        userLoginRequest.setEmail("username1");
        userLoginRequest.setPassword("password");
        loginUserResponse =userService.userLogin(userLoginRequest);
        assertTrue(loginUserResponse.getIsSuccessful());

    }
    @Test void userCanCreateABlog(){
        request.setEmail("username2");
        request.setPassword("password2");
        response= userService.registerUser(request);
        assertEquals(1, userRepository.count());

        userLoginRequest.setEmail("username2");
        userLoginRequest.setPassword("password2");
        loginUserResponse =userService.userLogin(userLoginRequest);
        assertTrue(loginUserResponse.getIsSuccessful());

        addBlogRequest.setBlogName("blogName");
        addBlogRequest.setDescription("description");
        List<String>  tagsList = new ArrayList<String>();
        tagsList.add("tags1");
        tagsList.add("tags2");
        tagsList.add("tags3");
        addBlogRequest.setTags(tagsList);
        addBlogRequest.setEmail(loginUserResponse.getEmail());

        userService.createBlog(addBlogRequest);
       Blog userBlog = userService.getUserBlogDetails(loginUserResponse.getUserId());

        assertNotNull(userBlog);

    }
    @Test
    public void  listAllUserBlogArticles() {
        request.setEmail("username2");
        request.setPassword("password2");
        response= userService.registerUser(request);
        assertEquals(1, userRepository.count());

        userLoginRequest.setEmail("username2");
        userLoginRequest.setPassword("password2");
        loginUserResponse =userService.userLogin(userLoginRequest);
        assertTrue(loginUserResponse.getIsSuccessful());

        addBlogRequest.setBlogName("blogName");
        addBlogRequest.setDescription("description");
        List<String>  tagsList = new ArrayList<String>();
        tagsList.add("tags1");
        tagsList.add("tags2");
        tagsList.add("tags3");
        addBlogRequest.setTags(tagsList);
        addBlogRequest.setEmail(loginUserResponse.getEmail());

        userService.createBlog(addBlogRequest);



        addArticleRequest.setTitle("articleTitle");
        addArticleRequest.setAuthor("articleAuthor");
        addArticleRequest.setDescription("articleDescription");
        addArticleRequest.setBody("articleBody");
        addArticleRequest.setEmail(loginUserResponse.getEmail());

        userService.createArticle(addArticleRequest);



       AppUserArticleResponse articles = userService.getUserAllArticlesList(loginUserResponse.getEmail());
        System.out.println(articles);
        assertEquals(1, articles.getArticles().size());


    }
    
    @Test
    void thatUserCanViewAArticle(){
        request.setEmail("username2");
        request.setPassword("password2");
        response= userService.registerUser(request);
        assertEquals(1, userRepository.count());

        userLoginRequest.setEmail("username2");
        userLoginRequest.setPassword("password2");
        loginUserResponse =userService.userLogin(userLoginRequest);
        assertTrue(loginUserResponse.getIsSuccessful());

        addBlogRequest.setBlogName("blogName");
        addBlogRequest.setDescription("description");
        List<String>  tagsList = new ArrayList<String>();
        tagsList.add("tags1");
        tagsList.add("tags2");
        tagsList.add("tags3");
        addBlogRequest.setTags(tagsList);
        addBlogRequest.setEmail(loginUserResponse.getEmail());

        userService.createBlog(addBlogRequest);



        addArticleRequest.setTitle("articleTitle");
        addArticleRequest.setAuthor("articleAuthor");
        addArticleRequest.setDescription("articleDescription");
        addArticleRequest.setBody("articleBody");
        addArticleRequest.setEmail(loginUserResponse.getEmail());

        userService.createArticle(addArticleRequest);
        
       SingleUserArticleResponse response = userService.getArticleInUserBlog("articleTitle");
       assertEquals("articleTitle", response.getTitle());
    }
    @Test
    public void thatUserCanDeleteAArticle(){
        request.setEmail("username2");
        request.setPassword("password2");
        response= userService.registerUser(request);
        assertEquals(1, userRepository.count());

        userLoginRequest.setEmail("username2");
        userLoginRequest.setPassword("password2");
        loginUserResponse =userService.userLogin(userLoginRequest);
        assertTrue(loginUserResponse.getIsSuccessful());

        addBlogRequest.setBlogName("blogName");
        addBlogRequest.setDescription("description");
        List<String>  tagsList = new ArrayList<String>();
        tagsList.add("tags1");
        tagsList.add("tags2");
        tagsList.add("tags3");
        addBlogRequest.setTags(tagsList);
        addBlogRequest.setEmail(loginUserResponse.getEmail());

        userService.createBlog(addBlogRequest);



        addArticleRequest.setTitle("articleTitle");
        addArticleRequest.setAuthor("articleAuthor");
        addArticleRequest.setDescription("articleDescription");
        addArticleRequest.setBody("articleBody");
        addArticleRequest.setEmail(loginUserResponse.getEmail());

        userService.createArticle(addArticleRequest);


        addArticleRequest.setTitle("articleTitle1");
        addArticleRequest.setAuthor("articleAuthor1");
        addArticleRequest.setDescription("articleDescription1");
        addArticleRequest.setBody("articleBody1");
        addArticleRequest.setEmail(loginUserResponse.getEmail());

        userService.createArticle(addArticleRequest);


        AppUserArticleResponse articles = userService.getUserAllArticlesList(loginUserResponse.getEmail());
        System.out.println(articles);
        assertEquals(2, articles.getArticles().size());

        userService.deleteArticleInUserBlog("articleTitle");

        assertEquals(1, articles.getArticles().size());


    }

}