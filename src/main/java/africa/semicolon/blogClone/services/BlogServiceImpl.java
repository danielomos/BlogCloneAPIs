package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.BlogRepository;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.responses.AddBlogResponse;
import africa.semicolon.blogClone.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ArticleService articleService;
//
//    @Autowired
//    UserService userService;


    @Override
    public Blog saveBlog(Blog blog) {

       blogRepository.save(blog);
       return blog;
    }

    @Override
    public Blog getBlogById(String id) {

        Blog blog = blogRepository.findBlogById(id);
        return blog;
    }

    @Override
    public void clearDatabases() {
        blogRepository.deleteAll();
    }

    public List<Article> getArticlesInaBlog(Blog userBlog) {
        String id = userBlog.getId();
        Blog blog = getBlogById(id);
        List<Article> articles = blog.getArticles();
        return articles;

    }
    public Blog getArticleBlog(Article article) {
        Blog foundBlog = new Blog();
        List<Blog> blogsInDb = getAllBlog();
        for (Blog eachBlogInDb : blogsInDb) {
            for(Article articleInBlog : eachBlogInDb.getArticles()){
                if(articleInBlog.equals(article)){
                    foundBlog = eachBlogInDb;
                }
            }
        }
        return foundBlog;
    }

    private List<Blog> getAllBlog() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs;
    }


    @Override
    public void deleteArticleInaBlog(String title) {
//        Article articleFromDb = articleService.findArticleByTitle(title);

        Article articleFromDb = articleService.getArticleInDb(title);
        Blog blogArticleBelongTo =  getArticleBlog(articleFromDb);
        blogArticleBelongTo.getArticles().remove(articleFromDb);

    }
@Override
    public String getArticle(String id, String title){
        Blog blog = getBlogById(id);
        List<Article> articles = blog.getArticles();
        for(Article article : articles) {
            if (article.getTitle().equalsIgnoreCase(title)) {
                return article.getId();
            }
        }
       return null;
}





}
