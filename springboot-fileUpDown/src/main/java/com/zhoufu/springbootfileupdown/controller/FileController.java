package com.zhoufu.springbootfileupdown.controller;

import cn.hutool.extra.servlet.ServletUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName : FileController
 * @Author : ZhouFu
 * @Date: 2023/4/26 10:14
 * @Description : 文件上传和下载
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Value("${filePath}")
    private String filePath;

    /**
     * 前往上传页面
     * @return 页面名称
     */
    @GetMapping({"/upload", ""})
    public String goIndex() {
        return "upload";
    }

    /**
     *  将文件保存到指定文件夹
     * @param file  单个文件
     * @param files 多个文件
     * @return      重定向到controller层中前往下载页面的url
     * @throws IOException
     * @RequestPart 注解最好加上，但是不加也可以
     */
    @PostMapping("/upload")
    public String uploadAndGoDownLoad(@RequestPart("file") MultipartFile file,
                                      @RequestPart("files") List<MultipartFile> files) throws IOException {

        /**
         * @RequestPart
         *      @RequestPart这个注解用在multipart/form-data表单提交请求的方法上。
         *      支持的请求方法的方式MultipartFile，属于Spring的MultipartResolver类。这个请求是通过http协议传输的
         * @RequestParam
         *      @RequestParam支持’application/json’，也同样支持multipart/form-data请求
         */

        //判断文件夹是否存在，不存在时，创建文件夹
        File directoryFile = new File(filePath);
        if (!directoryFile.exists()) {
            //创建多个文件夹
            directoryFile.mkdirs();
        }

        //判断文件是否为空，不为空时，保存文件
        if (!file.isEmpty()) {
            saveFile(file);
        }

        //判断上传文件个数是否为0
        if (files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                if (!multipartFile.isEmpty()) {
                    saveFile(multipartFile);
                }
            }
        }
        return "redirect:/file/goDownload";
    }

    /**
     * 保存所有的所有上传的文件名称，前往下载页面
     * @param model
     * @return 页面名称
     */
    @GetMapping("/goDownload")
    public String goDownload(Model model) {
        File file = new File(filePath);
        //判断文件夹是否存在
        if (file.exists()) {
            //获取文件夹下面的所有名称
            String[] list = file.list();
            model.addAttribute("fileNames", list);
        }
        return "download";
    }

    /**
     * 保存文件到指定位置
     * @param file 需要上传的文件
     * @throws IOException
     */
    public void saveFile(MultipartFile file) throws IOException {
        //获取文件名
        String name = file.getOriginalFilename();
        file.transferTo(new File(filePath + name));
    }


    /**
     * 方式一：
     *  使用Hutool实现文件下载
     * @param fileName 要下载的文件名
     * @param response
     *
     * 在pom.xml文件中导入hutool-extra的依赖，并设置CharacterEncoding为UTF-8。
     * 如果不设置CharacterEncoding，会出现中文文件名乱码
     */
    @GetMapping("/download/hutool")
    @ResponseBody
    public void downloadByHutool(@RequestParam(value = "fileName") String fileName,
                                 HttpServletResponse response) {
        //防止中文乱码
        response.setCharacterEncoding("UTF-8");
        ServletUtil.write(response, new File(filePath + fileName));
    }

    /**
     * 方式二：
     *  模仿hutool实现文件下载
     * @param fileName 要下载的文件名
     * @param response
     * @throws IOException
     */
    @GetMapping("/download/hutool/self")
    @ResponseBody
    public void downloadBySelfAndHutool(@RequestParam(value = "fileName") String fileName,
                                        HttpServletResponse response) throws IOException {

        //设置字符编码
        response.setCharacterEncoding("UTF-8");
        //以下模仿hutool进行相应设置
        //设置内容类型
        response.setHeader("Content-Type", "application/octet-stream");
        //设置文件名，是解决中文乱码的关键
        response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", URLEncoder.encode(fileName,"UTF-8")));

        //将文件取出，并写到response
        FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
        }
        fileInputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 通过返回值方式，实现文件下载
     * @param fileName  文件名
     * @return  文件流和请求头信息
     * @throws IOException
     */
    @GetMapping("/download/return")
    @ResponseBody
    public ResponseEntity<InputStreamResource> download(@RequestParam(value = "fileName") String fileName) throws IOException {
        // 读取文件
        String path = filePath + fileName;
        FileSystemResource file = new FileSystemResource(path);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
