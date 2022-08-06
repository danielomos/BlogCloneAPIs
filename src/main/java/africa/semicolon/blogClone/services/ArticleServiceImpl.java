package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepository  articleRepository;

    @Override
    public Article saveArticle(Article newArticle) {
        articleRepository.save(newArticle);
        return  newArticle;

    }
}
