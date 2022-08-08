package africa.semicolon.blogClone.dtos.requests;

import africa.semicolon.blogClone.data.models.Article;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data

public class AddBlogRequest {

    private String blogName;
    private String description;
    private List<String> tags;
    private String email;


}
