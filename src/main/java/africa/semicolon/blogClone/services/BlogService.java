package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.responses.AddBlogResponse;

import java.util.List;

public interface BlogService {
    public Blog saveBlog(Blog blog);


    Blog getBlogById(String id);

    public void clearDatabases();
//
////    Blog getArticlesInaBlog(Blog blog);
//    public List<Article> getArticlesInaBlog(Article article);
    public List<Article> getArticlesInaBlog(Blog userBlog);
    public Blog getArticleBlog(Article article);

    void deleteArticleInaBlog(String title);
}