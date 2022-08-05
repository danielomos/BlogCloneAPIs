package africa.semicolon.blogClone.utils;

import africa.semicolon.blogClone.data.models.Blog;
import africa.semicolon.blogClone.dtos.requests.AddBlogRequest;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static  void map(AddBlogRequest addBlogRequest, Blog inBlog){


        inBlog.setTags(addBlogRequest.getTags());
        inBlog.setDescription(addBlogRequest.getDescription());
        inBlog.setBlogName(addBlogRequest.getBlogName());



    }
}
