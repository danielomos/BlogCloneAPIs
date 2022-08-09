package africa.semicolon.blogClone.data.repositories;

import africa.semicolon.blogClone.data.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
 public Article findArticleById(String id);

}
