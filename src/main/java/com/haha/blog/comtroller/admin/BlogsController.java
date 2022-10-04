package com.haha.blog.comtroller.admin;

import com.github.pagehelper.PageInfo;
import com.haha.blog.mapper.BlogTypeMapper;
import com.haha.blog.pojo.Blog;
import com.haha.blog.pojo.BlogType;
import com.haha.blog.pojo.Message;
import com.haha.blog.service.BlogService;
import com.haha.blog.service.BlogTypeService;
import com.haha.blog.service.MessageService;
import com.haha.blog.service.impl.BlogTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class BlogsController {
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private BlogTypeService blogTypeService;
    
    @Autowired
    private MessageService messageService;
    
    
//    @GetMapping("/admin/blogs")
//    public String blogs(@RequestParam(value = "title",required = false) String title, 
//                        @RequestParam(value = "typeId",required = false) Integer typeId, 
//                        @RequestParam(value = "page",defaultValue = "1") Integer pageNum,
//                        Model model){
//        //types
//        List<BlogType> types = blogTypeService.getAllType();
//        model.addAttribute("types",types);
//        
//        //blog分类
//        PageInfo<Blog> blogs = blogService.getBlogByCondition(title,typeId,pageNum);
//        model.addAttribute("blogs",blogs);
//        
//        return "admin/blogs";
//    }

    @GetMapping("/admin/blogs")
    public String blogs(@RequestParam(value = "title",required = false) String title,
                         @RequestParam(value = "typeId",required = false) Integer typeId,
                         @RequestParam(value = "page",defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "msg",required = false) String msg,
                         Model model){
        //types
        List<BlogType> types = blogTypeService.getAllType();
        model.addAttribute("types",types);

        model.addAttribute("page",pageNum);
        model.addAttribute("title",title);
        model.addAttribute("typeId",typeId);
        BlogType type = blogTypeService.getTypeById(typeId);
        model.addAttribute("type",type);
        
        model.addAttribute("msg",msg);

        //blog分类
        PageInfo<Blog> blogs = blogService.getBlogByCondition(title,typeId,pageNum);
        model.addAttribute("blogs",blogs);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);
        
        return "admin/blogs";
    }
    
    @GetMapping("/admin/blogs-input")
    public String input(Model model){
        List<BlogType> types = blogTypeService.getAllType();
        model.addAttribute("types",types);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);
        
        return "admin/blogs-input";
    }

    @PostMapping("/blog")
    public String insertBlog(Blog blog,RedirectAttributes redirectAttributes){
        blog.setDate(new Timestamp(System.currentTimeMillis()));
        blogService.insertBlog(blog);
        
        if(blog.getStatus() == true){
            redirectAttributes.addAttribute("msg","提示：发布成功！");
        }else {
            redirectAttributes.addAttribute("msg","提示：保存成功！");
        }
        
        return "redirect:/admin/blogs";
    }
    
    @GetMapping("/admin/blog/delete")
    public String deleteBlog(@RequestParam("id") Integer id,
                             @RequestParam(value = "page",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "title",required = false) String title,
                             @RequestParam(value = "typeId",required = false) Integer typeId,
                             RedirectAttributes redirectAttributes
                             ){
        
        blogService.deleteBlog(id);

        redirectAttributes.addAttribute("page",pageNum);
        redirectAttributes.addAttribute("title",title);
        redirectAttributes.addAttribute("typeId",typeId);
        
        redirectAttributes.addAttribute("msg","提示：删除成功！");
        
        return "redirect:/admin/blogs";
    }

    @GetMapping("/admin/update")
    public String goUpdateBlog(@RequestParam("id") Integer id, Model model){

        List<BlogType> types = blogTypeService.getAllType();
        model.addAttribute("types",types);

        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog",blog);
        
        model.addAttribute("type",blogTypeService.getTypeById(blog.getTypeId()));

        //修改该类型的博客数量   减1
        BlogTypeServiceImpl blogTypeServiceImpl = (BlogTypeServiceImpl) blogTypeService;
        BlogTypeMapper blogTypeMapper = blogTypeServiceImpl.getBlogTypeMapper();
        blogTypeMapper.downCoount(blog.getTypeId());

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);

        return "admin/blogs-update";
    }
    
    @PutMapping("/blog")
    public String updateBlog(Blog blog,RedirectAttributes redirectAttributes){
        blog.setDate(new Timestamp(System.currentTimeMillis()));
        blogService.updateBlog(blog);

        //修改该类型的博客数量   加1
        BlogTypeServiceImpl blogTypeServiceImpl = (BlogTypeServiceImpl) blogTypeService;
        BlogTypeMapper blogTypeMapper = blogTypeServiceImpl.getBlogTypeMapper();
        blogTypeMapper.upCount(blog.getTypeId());

        if(blog.getStatus() == true){
            redirectAttributes.addAttribute("msg","提示：修改成功！");
        }else {
            redirectAttributes.addAttribute("msg","提示：保存成功！");
        }

        return "redirect:/admin/blogs";
    }
    
}
