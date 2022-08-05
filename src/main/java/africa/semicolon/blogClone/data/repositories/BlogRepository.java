package africa.semicolon.blogClone.data.repositories;

import africa.semicolon.blogClone.data.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String> {


}
