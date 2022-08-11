package africa.semicolon.blogClone.dtos.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class AppUserArticleResponse {
    private List<UserArticleListResponse> articles = new ArrayList<>();
    private String message;

}
