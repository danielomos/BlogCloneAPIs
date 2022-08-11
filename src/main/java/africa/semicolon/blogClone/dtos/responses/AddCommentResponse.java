package africa.semicolon.blogClone.dtos.responses;

import lombok.Data;

@Data
public class AddCommentResponse {
    private String id;
    private String name;
    private String commitMessage;


}
