package africa.semicolon.blogClone.data.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Article {
    @Id
private String id;
private String title;
private String description;
private String author;
private String body;
@DBRef
private List<Comments> commentsList =   new ArrayList<Comments>();
}
