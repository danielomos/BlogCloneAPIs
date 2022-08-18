package africa.semicolon.blogClone.dtos.responses;

import africa.semicolon.blogClone.data.models.Article;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class AppUserArticleResponse {
    private List<Article> articles = new ArrayList<>();
    private String message;

}
