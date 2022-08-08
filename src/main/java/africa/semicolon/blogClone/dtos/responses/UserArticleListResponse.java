package africa.semicolon.blogClone.dtos.responses;

import africa.semicolon.blogClone.data.models.Comments;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserArticleListResponse {
    private String id;
    private String title;
    private String description;
    private String author;
    private String body;
    private List<Comments> commentsList =   new ArrayList<Comments>();
    private String Message;
}
