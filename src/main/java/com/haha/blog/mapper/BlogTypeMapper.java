package com.haha.blog.mapper;

import com.haha.blog.pojo.BlogType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface BlogTypeMapper {


    List<BlogType> getAllType();

    BlogType getTypeById(Integer typeId);

    void upCount(Integer typeId);

    void downCoount(Integer typeId);

    void deleteType(Integer typeId);

    void insertBlogType(BlogType type);

    void updateBlogType(BlogType blogType);
}
