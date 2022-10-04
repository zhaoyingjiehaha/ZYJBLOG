package com.haha.blog.mapper;

import com.haha.blog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface BlogMapper {


    List<Blog> getAllBlogTrue();

    Blog getBlogById(Integer id);

    void insertBlog(Blog blog);

    void updateEye(Integer id);

    List<Blog> getBlogByType(Integer id);

    List<Blog> getBlogByCondition(@Param("title") String title, @Param("typeId") Integer typeId);

    void deleteBlog(Integer id);

    void updateBlog(Blog blog);

    List<Blog> getBlogByTypeTrue(Integer id);

    List<Blog> getBlogBySearch(String query);

    Integer getAllBlogCount();

    Integer getAllCustomerCount();
}
