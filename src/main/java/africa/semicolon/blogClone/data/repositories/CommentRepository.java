package africa.semicolon.blogClone.data.repositories;

import africa.semicolon.blogClone.data.models.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comments, String> {

}