package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Comments;
import africa.semicolon.blogClone.dtos.requests.AddCommentRequest;

public interface CommentService {


    Comments addCommit(AddCommentRequest addCommitRequest);
}
