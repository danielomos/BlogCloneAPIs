package africa.semicolon.blogClone.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Comments {
    @Id
    private String id;
    private String name;
    private String email;
    private String commitMessage;
}
