package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.User;

import java.util.List;

public interface ArticleService {
    Article saveArticle(Article newArticle);


    List<Article> getArticleList(User user);

    int getArticleCount(User user);

}
