package com.zhoufu.springbootcsv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : TestController
 * @Author : ZhouFu
 * @Date: 2023/8/14 14:05
 * @Description : 测试类
 */
public class TestController {

    public static void main(String[] args) {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        String fileName = "导出信息";
        String[] titles = new String[]{"A","B","C","D","E"};
        try(CsvWriter csvWriter = new CsvWriter(fileName, titles, request, response)) {
            // ....
            List<List<Object>> list = new ArrayList<>();
            csvWriter.write(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
