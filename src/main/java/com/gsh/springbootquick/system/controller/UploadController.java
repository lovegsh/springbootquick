package com.gsh.springbootquick.system.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author GSH
 * @create 2023/1/30 9:30
 */
@Controller
public class UploadController {

    @GetMapping("up")
    public String uploadPage(){
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(String name, @RequestParam(value = "multipartFile") MultipartFile multipartFile) throws IllegalStateException, IOException {
        Map<String, Object> map = new HashMap<>();
        if (multipartFile != null) {
            // 设置名称
            map.put("nameParam", name);
            // 设置文件名称
            map.put("filename", multipartFile.getOriginalFilename());
            // 设置文件类型
            map.put("contentType", multipartFile.getContentType());
            // 设置文件大小
            map.put("fileSize", multipartFile.getSize());
            // 创建文件名称
            String fileName = multipartFile.getOriginalFilename() + "."
                    + multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);
            // 获取到文件的路径信息
//            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//            String filePath = servletRequestAttributes.getRequest().getServletContext().getRealPath("/") + fileName;

            // 第一种
            String filePath = ResourceUtils.getURL("classpath:static").getPath();

            // 第二种，带‘/’获取ClassPath，不带‘/’获取此类所在的包路径
            String filePath2 = UploadController.class.getResource("").getPath();
            String filePath3 = this.getClass().getResource("").getPath();

            // 第三种 ClassLoader.getResource的path中不能以/开头，path是默认是从根目录下进行读取的
            String filePath4 = this.getClass().getClassLoader().getResource("").getPath();

            String filePath5 = new ClassPathResource("static").getPath();

            String filePath6 = ClassUtils.getDefaultClassLoader().getResource("static").getPath();

            String filePath7 = new File("src/main/resources").getPath();

            filePath += "/"+fileName;
            // 打印保存路径
            System.out.println("打印保存路径= "+filePath);
            System.out.println("2打印保存路径= "+filePath2);
            System.out.println("3打印保存路径= "+filePath3);
            System.out.println("4打印保存路径= "+filePath4);
            System.out.println("5打印保存路径= "+filePath5);
            System.out.println("6打印保存路径= "+filePath6);
            System.out.println("7打印保存路径= "+filePath7);
            System.out.println("文件名称== "+fileName+", "+ multipartFile.getContentType()+", "+ multipartFile.getSize());
            // 保存文件的路径信息
            map.put("filePath", filePath);
            // 创建文件
            File saveFile = new File(filePath);
            // 文件保存
            multipartFile.transferTo(saveFile);
            // 返回信息
            return map;
        } else {
            return "no file ";
        }
    }

    //request 方法
    @PostMapping("/upload2")
    @ResponseBody
    public Object upload2(String name, HttpServletRequest request) throws IllegalStateException, IOException {
        List<String> result = new ArrayList<String>();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartHttpServletRequest.getFiles("multipartFile");
            Iterator<MultipartFile> iterator = files.iterator();
            while (iterator.hasNext()) {
                // 取出每一个文件
                MultipartFile file = iterator.next();
                try {
                    // 保存上传信息
                    result.add(this.saveFile(file));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String saveFile(MultipartFile multipartFile) throws Exception {
        String filePath = "no file!!!";
        if (multipartFile != null && multipartFile.getSize() > 0) {
            // 创建文件名称
            String fileName = UUID.randomUUID() + "."
                    + multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);
            // 获取到文件的路径信息
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            filePath = servletRequestAttributes.getRequest().getServletContext().getRealPath("/") + fileName;
            // 打印保存路径
            System.out.println(filePath);
            File saveFile = new File(filePath);
            // 文件保存
            multipartFile.transferTo(saveFile);

        }
        return filePath;

    }

    public static void main(String[] args) {
        System.out.println(UploadController.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
