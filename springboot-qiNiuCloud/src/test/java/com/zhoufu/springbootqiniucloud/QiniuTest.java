package com.zhoufu.springbootqiniucloud;

import com.qiniu.common.QiniuException;
import com.zhoufu.springbootqiniucloud.modules.qiniu.service.IQiniuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootQiNiuCloudApplication.class)
public class QiniuTest {

    @Autowired
    private IQiniuService qiniuService;

    @Test
    public void testUpload() throws QiniuException {
        String result = qiniuService.uploadFile(new File("C:\\Users\\15153\\Pictures\\Default.jpg"), "helloworld");
        System.out.println("访问地址： " + result);
    }

    @Test
    public void testDelete() throws QiniuException {
        String result = qiniuService.delete("helloworld");
        System.out.println(result);
    }

}
