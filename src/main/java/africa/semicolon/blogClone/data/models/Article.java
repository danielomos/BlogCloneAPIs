package africa.semicolon.blogClone.data.models;


import java.util.ArrayList;
import java.util.List;

public class Article {
private String id;
private String title;
private String description;
private String author;
private String body;
private List<Comments> commentsList =   new ArrayList<Comments>();
}
