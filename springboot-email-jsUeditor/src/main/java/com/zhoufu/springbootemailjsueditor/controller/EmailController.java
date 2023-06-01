package com.zhoufu.springbootemailjsueditor.controller;

import com.zhoufu.springbootemailjsueditor.config.EmailConfig;
import com.zhoufu.springbootemailjsueditor.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author zf
 * @CreateTime 2022/5/28 9:24
 * @Description
 **/
@RestController
public class EmailController {

    @Autowired
    private EmailConfig emailConfig;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String subject, @RequestParam String text, @RequestParam("file") MultipartFile[] multipartFileList) {
        boolean flag= EmailUtil.sendEmailByParam(emailConfig, multipartFileList, subject, text);
        return flag?"发送成功!":"发送失败!";
    }

}
