package com.haha.blog.comtroller.admin;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.FriendLink;
import com.haha.blog.service.BlogService;
import com.haha.blog.service.FriendLinkService;
import com.haha.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FriendsController {

    @Autowired
    private FriendLinkService friendLinkService;
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private MessageService messageService;

    @GetMapping("/friends")
    public String friends(Model model){

        List<FriendLink> friendLinks = friendLinkService.getAllFriendLink();
        model.addAttribute("friendLinks",friendLinks);

        return "friends";
    }

    @GetMapping("/admin/friendlinks")
    public String friendlinks(@RequestParam(value = "msg",required = false) String msg,
                              @RequestParam(value = "page",defaultValue = "1") Integer pageNum,
                              Model model){

        model.addAttribute("msg",msg);

        PageInfo<FriendLink> friendLinks = friendLinkService.getAllFriendLinkPage(pageNum);
        model.addAttribute("friendLinks",friendLinks);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);

        return "admin/friendlinks";
    }

    @GetMapping("/admin/friendlinks-input")
    public String friendlinksInput(@RequestParam(value = "msg",required = false) String msg, Model model){

        model.addAttribute("msg",msg);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);

        return "admin/friendlinks-input";

    }

    @PostMapping("/friend")
    public String inputFriendLink(FriendLink friendLink, RedirectAttributes redirectAttributes){

        Integer count = friendLinkService.getCountSame(friendLink.getBlogaddress());

        if(count == 0){
            friendLinkService.inputFriendLink(friendLink);
            redirectAttributes.addAttribute("msg","提示：恭喜，操作成功！");
            return "redirect:/admin/friendlinks";
        }else {
            redirectAttributes.addAttribute("msg","提示：不能添加重复的友链!");
            return "redirect:/admin/friendlinks-input";
        }

    }
    
    @GetMapping("/admin/friendupdate")
    public String friendupdate(@RequestParam("id") Integer id,
                               @RequestParam(value = "msg",required = false) String msg,
                               Model model){
        
        FriendLink friendLink = friendLinkService.getFriendById(id);
        model.addAttribute("friendLink",friendLink);
        
        model.addAttribute("msg",msg);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);
        
        return "admin/friendlinks-update";
    }
    
    @PutMapping("/friend")
    public String updateFriend(FriendLink friendLink,RedirectAttributes redirectAttributes){
        Integer count = friendLinkService.getCountSame(friendLink.getBlogaddress());
        
        if(count == 0){
            redirectAttributes.addAttribute("msg","提示：修改成功！");
            friendLinkService.updateFriend(friendLink);
            return "redirect:/admin/friendlinks";
        }else {
            redirectAttributes.addAttribute("msg","提示：不能添加重复的友链!");
            redirectAttributes.addAttribute("id",friendLink.getId());
            return "redirect:/admin/friendupdate";
        }
    }
    
    @GetMapping("/admin/deletefriend")
    public String deleteFriend(@RequestParam("id")Integer id,
                               @RequestParam("page") Integer pageNum,
                               RedirectAttributes redirectAttributes){
        
        friendLinkService.deleteFriend(id);
        
        redirectAttributes.addAttribute("page",pageNum);
        redirectAttributes.addAttribute("msg","提示：删除成功！");
        
        return "redirect:/admin/friendlinks";
        
    }
}