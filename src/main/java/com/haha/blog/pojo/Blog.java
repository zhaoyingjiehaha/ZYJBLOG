package com.haha.blog.pojo;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Blog {
    
    private Integer id;
    private String title;
    private String content;
    private Integer typeId;
    private String commentId;//无字段
    private Timestamp date;
    private String profile;
    
    private Integer eye;
    
    private String origin;//来源：原创   转载   翻译
    private Boolean status;//草稿，发布
    
    
    private Integer messageCount;  //无该字段，用于显示该博客的评论数量
   
    
}
