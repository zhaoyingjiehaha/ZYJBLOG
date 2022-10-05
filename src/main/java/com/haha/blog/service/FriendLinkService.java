package com.haha.blog.service;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.FriendLink;

import java.util.List;

public interface FriendLinkService {

    Integer getCountSame(String blogaddress);

    void inputFriendLink(FriendLink friendLink);

    List<FriendLink> getAllFriendLink();

    PageInfo<FriendLink> getAllFriendLinkPage(Integer pageNum);

    FriendLink getFriendById(Integer id);

    void updateFriend(FriendLink friendLink);

    void deleteFriend(Integer id);
}
