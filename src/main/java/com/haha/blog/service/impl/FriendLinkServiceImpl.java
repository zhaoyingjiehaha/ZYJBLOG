package com.haha.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haha.blog.mapper.FriendLinkMapper;
import com.haha.blog.pojo.FriendLink;
import com.haha.blog.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;
    
    @Override
    public Integer getCountSame(String blogaddress) {
        return friendLinkMapper.getCountSame(blogaddress);
    }

    @Override
    public void inputFriendLink(FriendLink friendLink) {
        friendLinkMapper.inputFriendLink(friendLink);
    }

    @Override
    public List<FriendLink> getAllFriendLink() {
        return friendLinkMapper.getAllFriendLink();
    }

    @Override
    public PageInfo<FriendLink> getAllFriendLinkPage(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<FriendLink> allFriendLink = friendLinkMapper.getAllFriendLink();
        PageInfo<FriendLink> linkPage = new PageInfo<>(allFriendLink,5);
        return linkPage;
    }

    @Override
    public FriendLink getFriendById(Integer id) {
        return friendLinkMapper.getFriendById(id);
    }

    @Override
    public void updateFriend(FriendLink friendLink) {
        friendLinkMapper.updateFriend(friendLink);
    }

    @Override
    public void deleteFriend(Integer id) {
        friendLinkMapper.deleteFriend(id);
    }
}
