package africa.semicolon.blogClone.data.repositories;

import africa.semicolon.blogClone.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends MongoRepository<User, String> {
    public User findUserByUserName(String userName);
  public User findUserById(String userId);
}
