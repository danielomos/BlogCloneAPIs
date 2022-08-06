package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.responses.AddBlogResponse;

public interface BlogService {
    public Blog saveBlog(Blog blog);


    Blog getUserBlog(String id);
}