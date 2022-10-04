package com.haha.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haha.blog.mapper.BlogTypeMapper;
import com.haha.blog.pojo.BlogType;
import com.haha.blog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BlogTypeServiceImpl implements BlogTypeService {

    
    @Autowired
    private BlogTypeMapper blogTypeMapper;

    public BlogTypeMapper getBlogTypeMapper() {
        return blogTypeMapper;
    }

    @Override
    public List<BlogType> getAllType() {
        return blogTypeMapper.getAllType();
    }

    @Override
    public BlogType getTypeById(Integer typeId) {
        return blogTypeMapper.getTypeById(typeId);
    }

    @Override
    public PageInfo<BlogType> getTypePage(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<BlogType> allType = blogTypeMapper.getAllType();
        PageInfo<BlogType> typePage = new PageInfo<>(allType,5);
        return typePage;
    }

    @Override
    public void deleteType(Integer typeId) {
        blogTypeMapper.deleteType(typeId);
    }

    @Override
    public void insertBlogType(BlogType type) {
        blogTypeMapper.insertBlogType(type);
    }

    @Override
    public void updateBlogType(BlogType blogType) {
        blogTypeMapper.updateBlogType(blogType);
    }
    
    
}
