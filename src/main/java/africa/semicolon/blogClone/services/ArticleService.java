package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.User;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article saveArticle(Article newArticle);


    List<Article> getArticleList();

    int getArticleCount(User user);

    public void clearDatabases();



    Article getArticleInDb(String articleId);

    void deleteArticleInaBlog(String title);
}
