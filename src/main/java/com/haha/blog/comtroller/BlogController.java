package com.haha.blog.comtroller;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.Blog;
import com.haha.blog.pojo.BlogType;
import com.haha.blog.service.BlogService;
import com.haha.blog.service.BlogTypeService;
import com.haha.blog.service.MessageService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.css.Counter;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;
    
    @Autowired
    private MessageService messageService;


    @GetMapping("/")
    public String blogPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model) {
        
        PageInfo<Blog> blogs = blogService.getALlBlogTrue(pageNum);
        
        //messageCount
        for (Blog blog : blogs.getList()) {
            Integer messageCount = messageService.getBlogMessageCount(blog.getId());
            blog.setMessageCount(messageCount);
        }


        //blogPage
        model.addAttribute("blogs", blogs);

        //blogType
        List<BlogType> types = blogTypeService.getAllType();
        model.addAttribute("types", types);
        
        
        
//        Integer allBlogCount = blogService.getAllBlogCount();
//        model.addAttribute("allBlogCount",allBlogCount);
//        
//        Integer allCustomerCount = blogService.getAllCustomerCount();
//        model.addAttribute("allCustomerCount",allCustomerCount);
//        
//        Integer allMessageCount = messageService.getAllMessageCount();
//        model.addAttribute("allMessageCount",allMessageCount);
        
        return "index";
    }

    @GetMapping("/blog")
    public String blog(@RequestParam("id") Integer id, Model model) {
        blogService.updateEye(id);
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);

        Integer typeId = blog.getTypeId();
        BlogType blogType = blogTypeService.getTypeById(typeId);
        model.addAttribute("type", blogType);
        
        Integer count = messageService.getBlogMessageCount(id);
        model.addAttribute("count",count);

//        Integer allBlogCount = blogService.getAllBlogCount();
//        model.addAttribute("allBlogCount",allBlogCount);
//
//        Integer allCustomerCount = blogService.getAllCustomerCount();
//        model.addAttribute("allCustomerCount",allCustomerCount);
//
//        Integer allMessageCount = messageService.getAllMessageCount();
//        model.addAttribute("allMessageCount",allMessageCount);
                
        return "blog";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam("query") String query,
                         @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                         Model model) {
        List<BlogType> types = blogTypeService.getAllType();
        model.addAttribute("types", types);

        PageInfo<Blog> blogs = blogService.getBlogPageBySearch(query, pageNum);
        model.addAttribute("blogs", blogs);

        model.addAttribute("query", query);

         

        return "search";
    }

}
