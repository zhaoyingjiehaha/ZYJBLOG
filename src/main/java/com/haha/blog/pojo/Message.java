package com.haha.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    
    private Long id;
    private Integer blogId;
    private String nickname;
    private Boolean adminMessage;
    private Timestamp createTime;
    private String windowName;//windows版本
    private String browserName;//浏览器内核版本
    private String content;
    
    private Long parentMessageId;
    private String parentNickname;

    
    private Message parentMessage;
    private List<Message> replyMessages;
}