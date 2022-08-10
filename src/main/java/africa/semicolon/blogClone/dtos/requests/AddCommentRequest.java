package africa.semicolon.blogClone.dtos.requests;

import lombok.Data;

@Data
public class AddCommentRequest {


    private String name;
    private String email;
    private String commitMessage;
    private String articleTitle;

}
