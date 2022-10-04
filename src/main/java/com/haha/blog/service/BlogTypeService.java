package com.haha.blog.service;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.BlogType;

import java.util.List;

public interface BlogTypeService {

    List<BlogType> getAllType();

    BlogType getTypeById(Integer typeId);

    PageInfo<BlogType> getTypePage(Integer pageNum);

    void deleteType(Integer typeId);

    void insertBlogType(BlogType type);

    void updateBlogType(BlogType blogType);
}
