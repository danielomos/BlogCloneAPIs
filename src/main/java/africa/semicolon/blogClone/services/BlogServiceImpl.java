package africa.semicolon.blogClone.services;

import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.data.models.User;
import africa.semicolon.blogClone.data.repositories.BlogRepository;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;
import africa.semicolon.blogClone.dtos.responses.AddBlogResponse;
import africa.semicolon.blogClone.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;


    @Override
    public Blog saveBlog(Blog blog) {

       blogRepository.save(blog);
       return blog;
    }

    @Override
    public Blog getUserBlog(String id) {
        Blog userBlog = blogRepository.findBlogById(id);
        return userBlog;
    }


}
