package africa.semicolon.blogClone.utils;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.models.Comments;
import africa.semicolon.blogClone.dtos.requests.AddArticleRequest;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.responses.SingleUserArticleResponse;
import africa.semicolon.blogClone.dtos.responses.UserArticleListResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Mapper {
    public static  void map(AddBlogRequest addBlogRequest, Blog inBlog){

        inBlog.setTags(addBlogRequest.getTags());
        inBlog.setDescription(addBlogRequest.getDescription());
        inBlog.setBlogName(addBlogRequest.getBlogName());


    }


    public static void map(Article newArticle, AddArticleRequest addArticleRequest) {

        newArticle.setTitle(addArticleRequest.getTitle());
       newArticle.setAuthor(addArticleRequest.getAuthor());
        newArticle.setDescription(addArticleRequest.getDescription());
        newArticle.setBody(addArticleRequest.getBody());
    }

    public static void map(Article article, UserArticleListResponse eachResponse) {
//        List<Comments> comments = new ArrayList<Comments>();
//        for (Comments comment : )

        eachResponse.setTitle(article.getTitle());
        eachResponse.setDescription(article.getDescription());
        eachResponse.setBody(article.getBody());
        eachResponse.setId(article.getId());
        eachResponse.setAuthor(article.getAuthor());
        eachResponse.setCommentsList(article.getCommentsList());

    }


    public static void map(Article article, SingleUserArticleResponse articleResponse) {
        articleResponse.setAuthor(article.getAuthor());
        articleResponse.setTitle(article.getTitle());
        articleResponse.setBody(article.getBody());
        articleResponse.setDescription(article.getDescription());
        articleResponse.setCommentsList(article.getCommentsList());
        articleResponse.setId(article.getId());
    }
}
