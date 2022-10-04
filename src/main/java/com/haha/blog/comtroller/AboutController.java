package com.haha.blog.comtroller;

import com.haha.blog.service.BlogService;
import com.haha.blog.service.BlogTypeService;
import com.haha.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    
    
    @GetMapping("/about")
    public String about(Model model){
        
        return "about";
    }
}
