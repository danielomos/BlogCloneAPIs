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
public class Blog {
  @Id
  private String id;
  private String blogName;
  private String description;
  private List<String> tags = new ArrayList<>();

  @DBRef
  private List<Article> articles =new ArrayList<>();



}
