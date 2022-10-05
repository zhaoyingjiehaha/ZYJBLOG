package com.haha.blog.mapper;

import com.haha.blog.pojo.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface FriendLinkMapper {
    Integer getCountSame(String blogaddress);

    void inputFriendLink(FriendLink friendLink);

    List<FriendLink> getAllFriendLink();

    FriendLink getFriendById(@Param("id") Integer id);

    void updateFriend(FriendLink friendLink);

    void deleteFriend(Integer id);
}
