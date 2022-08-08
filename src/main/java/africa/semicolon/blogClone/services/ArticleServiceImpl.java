package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Article> getArticleList(User user) {

        return null;
    }

    @Override
    public int getArticleCount(User user) {
        List<Article> userArticles =  user.getBlog().getArticles();

        int count = userArticles.size();

        return count;
    }
}
