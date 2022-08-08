package africa.semicolon.blogClone.dtos.requests;

import lombok.Data;

@Data
public class AddArticleRequest {
    private String title;
    private String description;
    private String author;
    private String body;
    private String email;
}
