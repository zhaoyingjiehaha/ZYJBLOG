package com.haha.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haha.blog.mapper.BlogMapper;
import com.haha.blog.mapper.BlogTypeMapper;
import com.haha.blog.pojo.Blog;
import com.haha.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    
    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Override
    public PageInfo<Blog> getALlBlogTrue(Integer pageNum) {
        PageHelper.startPage(pageNum,8);
        List<Blog> allBlog = blogMapper.getAllBlogTrue();
        PageInfo<Blog> blogPage = new PageInfo<>(allBlog, 5);
        return blogPage;
    }

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = blogMapper.getBlogById(id);
        return blog;
    }

    @Override
    public void updateEye(Integer id) {
        blogMapper.updateEye(id);
    }

    @Override
    public void insertBlog(Blog blog) {
        blogMapper.insertBlog(blog);
        Integer typeId = blog.getTypeId();
        blogTypeMapper.upCount(typeId);
    }

    @Override
    public List<Blog> getBlogByType(Integer id) {
        return blogMapper.getBlogByType(id);
    }

    @Override
    public PageInfo<Blog> getBlogByCondition(String title, Integer typeId,Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Blog> allBlog = blogMapper.getBlogByCondition(title,typeId);
        PageInfo<Blog> blogPage = new PageInfo<>(allBlog, 5);
        return blogPage;
    }

    @Override
    public void deleteBlog(Integer id) {
        Blog blog = blogMapper.getBlogById(id);
        Integer typeId = blog.getTypeId();
        blogMapper.deleteBlog(id);
        
        blogTypeMapper.downCoount(typeId);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogMapper.updateBlog(blog);
    }

    @Override
    public List<Blog> getBlogByTypeTrue(Integer id) {
        return blogMapper.getBlogByTypeTrue(id);
    }

    @Override
    public PageInfo<Blog> getBlogPageBySearch(String query,Integer pageNum) {
        
        PageHelper.startPage(pageNum,5);
        List<Blog> blogBySearch = blogMapper.getBlogBySearch(query);
        PageInfo<Blog> blogPage = new PageInfo<>(blogBySearch,5);

        return blogPage;
    }

    @Override
    public Integer getAllBlogCount() {
        return blogMapper.getAllBlogCount();
    }

    @Override
    public Integer getAllCustomerCount() {
        return blogMapper.getAllCustomerCount();
    }
}
