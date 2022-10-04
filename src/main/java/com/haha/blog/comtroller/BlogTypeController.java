package com.haha.blog.comtroller;

import com.haha.blog.pojo.Blog;
import com.haha.blog.pojo.BlogType;
import com.haha.blog.service.BlogService;
import com.haha.blog.service.BlogTypeService;
import com.haha.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogTypeController {
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private BlogTypeService blogTypeService;
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/types")
    public String types(@RequestParam(value = "id",defaultValue = "1") Integer id, Model model){

        List<BlogType> types = blogTypeService.getAllType();
        for (BlogType type : types) {
            List<Blog> blogs = blogService.getBlogByType(type.getId());
            for (Blog blog : blogs) {
                if(blog.getStatus() == false){
                    type.setCount(type.getCount()-1);
                }
            }
        }
        model.addAttribute("types",types);

        List<Blog> blogs = blogService.getBlogByTypeTrue(id);

        for (Blog blog : blogs) {
            Integer messageCount = messageService.getBlogMessageCount(blog.getId());
            blog.setMessageCount(messageCount);
        }
        
        model.addAttribute("blogs",blogs);

         
        
        return "types";
    }
    
}
