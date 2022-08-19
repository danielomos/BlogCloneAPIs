package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.ArticleRepository;
import africa.semicolon.blogClone.exceptions.BlogCloneErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepository  articleRepository;

//    @Autowired
//    BlogService blogService;

    @Override
    public Article saveArticle(Article newArticle) {
        articleRepository.save(newArticle);
        return  newArticle;

    }

    @Override
    public List<Article> getArticleList() {
       List<Article> articles =  articleRepository.findAll();
       return articles;
//        return articles.stream().map(Article::getId).collect(Collectors.toList());  ;
    }

    @Override
    public int getArticleCount(User user) {
        List<Article> userArticles =  user.getBlog().getArticles();

        int count = userArticles.size();

        return count;
    }

    @Override
    public void clearDatabases() {
        articleRepository.deleteAll();
    }

    @Override
    public Article getArticleInDb(String id) {

        if (articleRepository.findAll().
                stream().noneMatch(((article -> article.getId().equalsIgnoreCase(id))))) {
            throw new BlogCloneErrorException("article not found");

        }
        Article article = new Article();
        List<Article> articles = getArticleList();
        for (Article eachArticle : articles) {
            if (eachArticle.getId().equalsIgnoreCase(id))
             article = eachArticle;

        }

  return article;

    }
    @Override
    public void deleteArticleInaBlog(String id) {


        articleRepository.deleteById(id);
    }
    @Override
    public void deleteArticleInaBlog(Article article) {
//        Article articleToDelete = getArticleInDb(title);

        articleRepository.delete(article);
    }
    @Override
    public Article findArticleByTitle(String title) {
     Article article = articleRepository.findArticleByTitle(title);
     return article;
    }

@Override
    public Article findArticleById(String id) {
        Article article = articleRepository.findArticleById(id);
        return article;
}

@Override
    public Long count (){
       Long count =  articleRepository.count();
       return count;
}
}
