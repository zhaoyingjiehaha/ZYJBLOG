package com.haha.blog.service;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.Message;

import java.util.List;

public interface MessageService {

    List<Message> listMessage(Integer blogId);

    void saveMessage(Message message);

    void deleteMessage(Long id, Integer blogId);

    Integer getBlogMessageCount(Integer id);

    Integer getAllMessageCount();
    
    PageInfo<Message> pageMessage(Integer blogId, Integer pageNum);

    //根据最新日期在前排序查询
    List<Message> listMessage2(Integer blogId);

    Integer getAllMessagesCount();
}
