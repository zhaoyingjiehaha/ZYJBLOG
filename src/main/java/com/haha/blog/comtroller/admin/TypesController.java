package com.haha.blog.comtroller.admin;

import com.github.pagehelper.PageInfo;
import com.haha.blog.pojo.Blog;
import com.haha.blog.pojo.BlogType;
import com.haha.blog.pojo.Message;
import com.haha.blog.service.BlogService;
import com.haha.blog.service.BlogTypeService;
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
public class TypesController {
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private BlogTypeService blogTypeService;
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/admin/types")
    public String types(@RequestParam(value = "page",defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "msg",required = false) String msg,
                        Model model){
        
        PageInfo<BlogType> types = blogTypeService.getTypePage(pageNum);
        model.addAttribute("types",types);
        
        model.addAttribute("msg",msg);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);
        
        return "admin/types";
    }
    
    @GetMapping("/admin/types/delete")
    public String deleteType(@RequestParam("id") Integer typeId,
                             @RequestParam(value = "page",defaultValue = "1") Integer pageNum,
                             RedirectAttributes redirectAttributes){

        List<Blog> blogByType = blogService.getBlogByType(typeId);
        for (Blog blog : blogByType) {
            blog.setTypeId(null);
            blogService.updateBlog(blog);
        }
        
        blogTypeService.deleteType(typeId);
        
        redirectAttributes.addAttribute("page",pageNum);
        redirectAttributes.addAttribute("msg","提示：删除成功！");
        
        return "redirect:/admin/types";
        
    }
    
    @GetMapping("/admin/types-input")
    public String inputType(@RequestParam(value = "msg",required = false) String msg, Model model){
        model.addAttribute("msg",msg);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);
        
        return "admin/types-input";
    }
    
    @PostMapping("/type")
    public String insertType(BlogType type,RedirectAttributes redirectAttributes){
        String typeName = type.getTypeName();
        List<BlogType> allType = blogTypeService.getAllType();
        Boolean bool = Boolean.TRUE;
        for (BlogType blogType : allType) {
            if(blogType.getTypeName().equals(typeName)){
                bool = Boolean.FALSE;
                break;
            }
        }

        if (bool){
            blogTypeService.insertBlogType(type);
            redirectAttributes.addAttribute("msg","提示：添加类型成功！");
            return "redirect:/admin/types";
        }else {
            redirectAttributes.addAttribute("msg","提示：不能添加重复的分类！");
            return "redirect:/admin/types-input";
        }
        
    }
    
    @GetMapping("/admin/update-type")
    public String GoToUpdateType(@RequestParam("id") Integer id,
                                 @RequestParam(value = "msg",required = false) String msg,
                                 Model model){

        BlogType type = blogTypeService.getTypeById(id);
        model.addAttribute("type",type);
        model.addAttribute("msg",msg);

        Integer allBlogCount = blogService.getAllBlogCount();
        model.addAttribute("allBlogCount",allBlogCount);

        Integer allCustomerCount = blogService.getAllCustomerCount();
        model.addAttribute("allCustomerCount",allCustomerCount);

        Integer allMessageCount = messageService.getAllMessageCount();
        model.addAttribute("allMessageCount",allMessageCount);

        Integer allMessagesCount = messageService.getAllMessagesCount();
        model.addAttribute("allMessagesCount",allMessagesCount);

        return "admin/types-update";
    }
   
    @PutMapping("/type")
    public String updateType(BlogType blogType,RedirectAttributes redirectAttributes){
        String typeName = blogType.getTypeName();
        List<BlogType> allType = blogTypeService.getAllType();
        Boolean bool = Boolean.TRUE;
        for (BlogType type : allType) {
            if(type.getTypeName().equals(typeName)){
                bool = Boolean.FALSE;
                break;
            }
        }
        
        if(bool){
            blogTypeService.updateBlogType(blogType);
            redirectAttributes.addAttribute("msg","提示：分类名称修改成功！");
            return "redirect:/admin/types";
        }else {
            redirectAttributes.addAttribute("msg","提示：不能添加重复的分类！");
            redirectAttributes.addAttribute("id",blogType.getId());
            return "redirect:/admin/update-type";
        }
        
    }
    
    
}
