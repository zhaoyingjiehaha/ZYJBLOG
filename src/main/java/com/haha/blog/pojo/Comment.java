package com.haha.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    
    private Long id;
    private String content;
    private Timestamp date;
    private Integer blogId;
    private String authorName;
    private String authorEmail;
    private Long parent_id;
    private Integer level;
}
