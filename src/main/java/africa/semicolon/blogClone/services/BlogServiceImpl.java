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


}
