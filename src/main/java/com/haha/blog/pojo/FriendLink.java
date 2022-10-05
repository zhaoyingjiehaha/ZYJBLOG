package com.haha.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FriendLink {

    private Integer id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;

}
