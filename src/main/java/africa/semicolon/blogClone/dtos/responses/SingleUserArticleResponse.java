package africa.semicolon.blogClone.dtos.responses;

import africa.semicolon.blogClone.data.models.Comments;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
@Data
public class SingleUserArticleResponse {

    private String id;
    private String title;
    private String description;
    private String author;
    private String body;

    private List<Comments> commentsList = new ArrayList<Comments>();
  }

