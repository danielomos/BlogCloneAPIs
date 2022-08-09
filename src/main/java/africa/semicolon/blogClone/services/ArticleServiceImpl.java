package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.ArticleRepository;
import africa.semicolon.blogClone.exceptions.BlogCloneErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepository  articleRepository;

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
    public Article getArticle(String title) {

        if (articleRepository.findAll().
                stream().noneMatch(((article -> article.getTitle().equalsIgnoreCase(title))))) {
            throw new BlogCloneErrorException("article not found");

        }
        List<Article> articles = getArticleList();
        for (Article eachArticle : articles) {
            if (eachArticle.getTitle().equalsIgnoreCase(title))
                Article foundArticle = eachArticle;
        }


    }
//    private  Article findArticleByTitle(String title) {
//        articleRepository.findArticleByTitle(title)
//    }


}
