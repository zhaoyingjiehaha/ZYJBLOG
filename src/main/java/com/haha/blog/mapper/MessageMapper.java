package com.haha.blog.mapper;

import com.haha.blog.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface MessageMapper {


    void saveMessage(Message message);

    List<Message> findByParentIdNull(@Param("parentId") long parentId,@Param("blogId") Integer blogId);

    void deleteMessage(Long id);

    Integer getBlogMessageCount(Integer id);

    Integer getAllMessageCount();

    List<Message> findByParentIdNull2(@Param("parentId") long parentId,@Param("blogId") Integer blogId);

    Integer getAllMessagesCount();
}
