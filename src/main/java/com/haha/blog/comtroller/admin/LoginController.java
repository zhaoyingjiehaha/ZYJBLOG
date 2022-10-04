package com.haha.blog.comtroller.admin;

import com.haha.blog.pojo.User;
import com.haha.blog.service.BlogService;
import com.haha.blog.service.BlogTypeService;
import com.haha.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private BlogService blogService;
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping(value = {"/admin/login","/admin","/login"})
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model){
        if(user.getNickname().equals("haha") && user.getPassword().equals("123")){
            user.setNickname("zyj");
            user.setEmail("2635879218@qq.com");
            session.setAttribute("loginUser",user);
            return "redirect:/admin/index";
        }else {
            model.addAttribute("msg","用户名和密码错误");
            return "admin/login";
        }
    }

    @GetMapping("/admin/index")
    public String adminIndex(HttpSession session, Model model){
        /**
         * 由拦截器LoginInterceptor拦截
         */
        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);

        return "admin/index";
    }
    
    //登出
    @GetMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

}
