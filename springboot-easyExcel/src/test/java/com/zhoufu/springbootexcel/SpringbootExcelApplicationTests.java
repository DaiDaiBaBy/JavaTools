package com.zhoufu.springbootexcel;

import com.alibaba.excel.EasyExcel;
import com.zhoufu.springbootexcel.domain.DemoData;
import com.zhoufu.springbootexcel.listener.ExcelReadListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SpringbootExcelApplicationTests {

    @Test
    public void contextLoads() {
        
    }

    /**
     * excel写操作，
     * 把数据写入excel， 生成xlsx文件
     */
    @Test
    public void excelWrite() {
        //实现excel写的操作
        //1、设置写入文件夹地址和excel文件名称
        String fileName = "D:\\workspace\\develop\\JavaTools\\springboot-easyExcel\\excel\\学生.xlsx";

        //2、调用EasyExcel里面方法实现写的操作
        //write两个参数：参数1：文件路径名称   参数2：参数实体类class
        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
    }

    /**
     * excel读操作
     * 把数据从excel中读出来，  执行后面操作
     */
    @Test
    public void excelRead(){
        //实现excel读的操作
        String fileName = "D:\\workspace\\develop\\JavaTools\\springboot-easyExcel\\excel\\学生.xlsx";
        EasyExcel.read(fileName,DemoData.class, new ExcelReadListener()).sheet().doRead();
    }



    /**  实现Excel写操作
     * 创建一个测试类，在该类中创建方法循环设置要添加到Excel的数据
     * @return
     */
    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> getData(){
        ArrayList<DemoData> demoData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("Eric" + i);
            demoData.add(data);
        }
        return demoData;
    }

}
