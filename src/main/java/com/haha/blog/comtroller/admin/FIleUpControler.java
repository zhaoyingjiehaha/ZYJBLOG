package com.haha.blog.comtroller.admin;

import com.haha.blog.pojo.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
public class FIleUpControler {

    @RequestMapping("/imgup")
    public String upload(@RequestPart(value = "images",required = false) MultipartFile[] photos,
                         @RequestParam(value = "photoName",required = false) String photoName,
                         @RequestParam(value = "blogId",required = false) Integer blogId,
                         RedirectAttributes redirectAttributes) throws IOException {

        log.info("上传的信息：photosLength={}", photos.length);
        
        

        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                //获取上传的文件的文件名
                String fileName = photo.getOriginalFilename();
                //获取上传的文件的后缀名
                String hzName = fileName.substring(fileName.lastIndexOf("."));
                //获取uuid
//                String uuid = UUID.randomUUID().toString();
                //拼接一个新的文件名
                fileName = photoName + hzName;
                /*//获取ServletContext对象
                ServletContext servletContext = session.getServletContext();
                //获取当前工程下photo目录的真实路径
                String photoPath = servletContext.getRealPath("photo");
                //创建photoPath所对应的File对象
                File file = new File(photoPath);
                //判断file所对应目录是否存在
                if(!file.exists()){
                    file.mkdir();
                }
                String finalPath = photoPath + File.separator + fileName;
                //上传文件
                photo.transferTo(new File(finalPath));*/

                File file = new File("src/main/resources/static/images");
//            log.info(photoPath);
                //创建photoPath所对应的File对象
//            File file = new File(photoPath);
                //判断file所对应目录是否存在
                if(!file.exists()){
                    file.mkdir();
                }
                String finalPath = file.getAbsolutePath() + File.separator + fileName;

                log.info(finalPath);
                //上传文件
                photo.transferTo(new File(finalPath));
            }
        }
        if(blogId != null){
            redirectAttributes.addAttribute("id",blogId);
            return "redirect:/admin/update";
        }else {
            return "redirect:/admin/blogs-input";
        }
        
    }
}
