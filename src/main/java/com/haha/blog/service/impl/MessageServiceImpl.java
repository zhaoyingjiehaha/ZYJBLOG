package com.haha.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haha.blog.mapper.MessageMapper;
import com.haha.blog.pojo.Message;
import com.haha.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    
    @Autowired
    private MessageMapper messageMapper;
    
    private List<Message> tempReplys = new ArrayList<>();
    
    @Override
    public List<Message> listMessage(Integer blogId) {
        //查询出父节点
        List<Message> messages = messageMapper.findByParentIdNull(Long.parseLong("-1"),blogId);
        for (Message message : messages) {
            Long id = message.getId();
            String parentNickName = message.getNickname();
            //查询出子一级留言
            List<Message> childMessages = messageMapper.findByParentIdNull(id,blogId);
            combineChildren(childMessages,parentNickName,blogId);
            //查询完该留言的所有子留言后，设置进去
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }

    private void combineChildren(List<Message> childMessages, String parentNickName, Integer blogId) {
        //判断是否有子二级留言
        if(childMessages.size() > 0){
            //循环找出子留言的id
            for (Message childMessage : childMessages) {
                String newParentNickName = childMessage.getNickname();
                childMessage.setParentNickname(parentNickName);
                tempReplys.add(childMessage);
                Long newParentId = childMessage.getId();
                //查询子三级以及所有留言
                recursively(newParentId, newParentNickName, blogId);
            }
        }
    }

    private void recursively(Long newParentId, String newParentNickName, Integer blogId) {
        //根据子一级留言的id找到子二级留言
        List<Message> replyMessages = messageMapper.findByParentIdNull(newParentId,blogId);
        
        if (replyMessages.size() > 0){
            for (Message replyMessage : replyMessages) {
                String parentNickName = replyMessage.getNickname();
                replyMessage.setParentNickname(newParentNickName);
                Long replyId = replyMessage.getId();
                tempReplys.add(replyMessage);
                //循环迭代找出子集回复
                recursively(replyId,parentNickName,blogId);
            }
        }
    }

    @Override
    public void saveMessage(Message message) {
        messageMapper.saveMessage(message);
    }

    //删除留言及其所有子留言
    @Override
    public void deleteMessage(Long id, Integer blogId) {
        messageMapper.deleteMessage(id);
        List<Message> childMessages = messageMapper.findByParentIdNull(id, blogId);
        if(childMessages.size() > 0){
            for (Message childMessage : childMessages) {
                Long newId = childMessage.getId();
                this.deleteMessage(newId,blogId);
            }
        }
    }

    @Override
    public Integer getBlogMessageCount(Integer id) {
        return messageMapper.getBlogMessageCount(id);
    }

    @Override
    public Integer getAllMessageCount() {
        return messageMapper.getAllMessageCount();
    }

    @Override
    public Integer getAllMessagesCount() {
        return messageMapper.getAllMessagesCount();
    }

    //留言板留言分页查询
    @Override
    public PageInfo<Message> pageMessage(Integer blogId, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Message> allMessage = listMessage2(blogId);
        PageInfo<Message> messages = new PageInfo<>(allMessage,5);
        return messages;
    }

    //根据最新日期在前排序查询
    @Override
    public List<Message> listMessage2(Integer blogId) {
        //查询出父节点
        List<Message> messages = messageMapper.findByParentIdNull2(Long.parseLong("-1"),blogId);
        for (Message message : messages) {
            Long id = message.getId();
            String parentNickName = message.getNickname();
            //查询出子一级留言
            List<Message> childMessages = messageMapper.findByParentIdNull(id,blogId);
            combineChildren(childMessages,parentNickName,blogId);
            //查询完该留言的所有子留言后，设置进去
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }
}
