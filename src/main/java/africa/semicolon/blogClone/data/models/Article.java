package africa.semicolon.blogClone.data.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Article {
private String id;
private String title;
private String description;
private String author;
private String body;
private List<Comments> commentsList =   new ArrayList<Comments>();
}
