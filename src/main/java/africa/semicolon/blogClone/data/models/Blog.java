package africa.semicolon.blogClone.data.models;




import java.util.ArrayList;
import java.util.List;


public class Blog {
  private String id;
  private String title;
  private String author;
  private List<String> tags;

  private List<Article> articles =new ArrayList<>();
}
