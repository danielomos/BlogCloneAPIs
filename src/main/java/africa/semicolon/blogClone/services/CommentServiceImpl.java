package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Article;
import africa.semicolon.blogClone.data.models.Comments;
import africa.semicolon.blogClone.data.repositories.CommentRepository;
import africa.semicolon.blogClone.dtos.requests.AddCommentRequest;
import africa.semicolon.blogClone.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    ArticleService articleService;
    @Override
    public Comments addCommit(AddCommentRequest addCommitRequest) {
        Article article = articleService.getArticleInDb(addCommitRequest.getArticleTitle());
        Comments comment = new Comments();
        Mapper.map(addCommitRequest, comment);

        commentRepository.save(comment);
        List<Comments> articleCommentList = article.getCommentsList();

        articleCommentList.add(comment);
        article.setCommentsList(articleCommentList);
         articleService.saveArticle(article);
       return  comment;





    }

}
