package com.haha.blog.service;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.Blog;
import com.haha.blog.pojo.BlogType;

import java.util.List;


public interface BlogService {

    PageInfo<Blog> getALlBlogTrue(Integer pageNum);

    Blog getBlogById(Integer id);

    void insertBlog(Blog blog);


    List<Blog> getBlogByType(Integer id);
    
    List<Blog> getBlogByTypeTrue(Integer id);
    

    PageInfo<Blog> getBlogByCondition(String title, Integer typeId,Integer pageNum);

    void deleteBlog(Integer id);
    
    void updateEye(Integer id);

    void updateBlog(Blog blog);

    PageInfo<Blog> getBlogPageBySearch(String query,Integer pageNum);

    Integer getAllBlogCount();

    Integer getAllCustomerCount();
}
