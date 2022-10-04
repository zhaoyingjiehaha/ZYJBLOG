package com.haha.blog.comtroller;

import com.haha.blog.service.BlogService;
import com.haha.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllCountController {

    /**
     * 只适用非admin下页面allCoount的数据加载，admin下的加载无法成功
     */
    @Autowired
    private BlogService blogService;

    @Autowired
    private MessageService messageService;
    

    @GetMapping("/allCount")
    public String allCount(Model model){

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);
        
        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);

        return "common::allCount";
    }
}
