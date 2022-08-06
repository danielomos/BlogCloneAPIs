package africa.semicolon.blogClone.data.repositories;

import africa.semicolon.blogClone.data.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {
    Blog getBlogById(String id);

    List<Blog> getAllBlog();

}
