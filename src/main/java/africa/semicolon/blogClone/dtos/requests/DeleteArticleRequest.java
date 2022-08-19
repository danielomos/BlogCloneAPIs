package africa.semicolon.blogClone.dtos.requests;

import lombok.Data;
@Data
public class DeleteArticleRequest {

    private String articleTitle;
    private String blogId;
}
