package africa.semicolon.blogClone.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class Comments {

    private String id;
    private String name;
    private String email;
    private String commitMessage;
}
