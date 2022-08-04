package africa.semicolon.blogClone.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String userName;
    private String password;

    @DBRef
    private Blog Blog;

}
