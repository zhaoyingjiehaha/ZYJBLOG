package com.haha.blog.comtroller;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.Message;
import com.haha.blog.pojo.User;
import com.haha.blog.service.BlogService;
import com.haha.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class MessageController {
    
    @Autowired
    private BlogService blogService;
    @Autowired
    private MessageService messageService;
    
    //新增评论
    @PostMapping("/messages")
    public String post(Message message, HttpSession session, Model model){
        User user = (User) session.getAttribute("loginUser");
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //设置栈主adminMessage
        if(user != null){
            message.setAdminMessage(true);
            message.setNickname("zyj");
        }else {
            message.setAdminMessage(false);
        }
        
        if(message.getParentMessage().getId()!=null){
            message.setParentMessageId(message.getParentMessage().getId());
        }
        
        System.out.println(message);
        messageService.saveMessage(message);
        List<Message>  messages = messageService.listMessage(message.getBlogId());
        model.addAttribute("messages",messages);
        return "blog :: messageList";//blog页面的th:fragment="messageList"
        
    }

    //查询评论  评论显示
    @GetMapping("/messagecomment")
    public String messages(@RequestParam("id") Integer blogId, Model model){
        List<Message> messages = messageService.listMessage(blogId);
        model.addAttribute("messages",messages);
        return "blog::messageList";
    }
    
    //删除评论
    @GetMapping("/messagesdelete")
    public String deleteMessage(@RequestParam("id") Long id,
                                @RequestParam("blogId") Integer blogId,
                                RedirectAttributes redirectAttributes,Model model){
        messageService.deleteMessage(id,blogId);
//        List<Message> messages = messageService.listMessage(blogId);
//        model.addAttribute("messages",messages);
//        return "blog::messageList";
        
        redirectAttributes.addAttribute("id",blogId);
        return "redirect:/blog";
    }
    
    
//    留言板
    @GetMapping("/message")
    public String message(@RequestParam(value = "page",defaultValue = "1") Integer pageNum ,
                          @RequestParam(value = "parentNickname",required = false) String parentNickname,
                          /*@RequestParam(value = "parentMessage",required = false) Message message,*/
                          @RequestParam(value = "parentMessageId",required = false) Long parentMessageId,
                          Model model){

        PageInfo<Message> messages = messageService.pageMessage(-1, pageNum);
        model.addAttribute("messages",messages);

        model.addAttribute("parentNickname",parentNickname);
//        model.addAttribute("parentMessage",message);
        model.addAttribute("parentMessageId",parentMessageId);
        

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);

        return "message"; 
    }
    
    @PostMapping("/message")
    public String insertMessage(Message message, HttpSession session,
            @RequestParam("page") Integer pageNum,RedirectAttributes redirectAttributes){

        User user = (User) session.getAttribute("loginUser");
        message.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //设置栈主adminMessage
        if(user != null){
            message.setAdminMessage(true);
            message.setNickname("zyj");
        }else {
            message.setAdminMessage(false);
        }

//        if(message.getParentMessage().getId()!=null){
//            message.setParentMessageId(message.getParentMessage().getId());
//        }

        System.out.println(message);
        messageService.saveMessage(message);
        
        redirectAttributes.addAttribute("page",pageNum);
        
        return "redirect:/message";
    }
    
    //恢复留言
    @GetMapping("/reply")
    public String replyMessage(@RequestParam("parentNickname") String parentNickname,
                               @RequestParam("parentMessageId") Long parentMessageId,
                               @RequestParam("page") Integer pageNum,
                               RedirectAttributes redirectAttributes){
        
        redirectAttributes.addAttribute("parentNickname",parentNickname);

//        Message message = new Message();
//        message.setId(parentMessageId);
//        redirectAttributes.addAttribute("parentMessage",message);
        redirectAttributes.addAttribute("parentMessageId",parentMessageId);
        
        redirectAttributes.addAttribute("page",pageNum);
        
        return "redirect:/message";
    }
    
    //删除留言
    @GetMapping("/messagedelete")
    public String messageDelete(@RequestParam("id") Long id,
                                @RequestParam("page") Integer pageNum,
                                RedirectAttributes redirectAttributes){
        
        messageService.deleteMessage(id,-1);
        
        redirectAttributes.addAttribute("page",pageNum);
        
        return "redirect:/message";
        
    }
    
}
