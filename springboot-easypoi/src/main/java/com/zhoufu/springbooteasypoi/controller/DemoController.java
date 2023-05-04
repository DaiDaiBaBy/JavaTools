package com.zhoufu.springbooteasypoi.controller;

import com.zhoufu.springbooteasypoi.domain.UserExportVO;
import com.zhoufu.springbooteasypoi.utils.utilSecond.ExcelPoiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName : DemoController
 * @Author : ZhouFu
 * @Date: 2023/5/4 10:25
 * @Description : 提供接口
 */
@Api(tags = "excelPoi接口Demo")
@RestController
@RequestMapping("/excelDemo")
public class DemoController {

    @ApiOperation(value = "导出excel接口", notes = "导出excel接口")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        //查询要导出的数据
        //List<UserExportVO> users = userService.getUserExportList();
        // 模拟数据
        List<UserExportVO> users = new ArrayList<>();
        users.add(new UserExportVO("悟纤",1,new Date(),"18688888888","1688@qq.com","https://img0.baidu.com/it/u=1115130400,112519339&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=508","01"));
        users.add(new UserExportVO("师傅",1,new Date(),"18666666666","1888@qq.com","https://img0.baidu.com/it/u=1115130400,112519339&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=508","02"));

        ExcelPoiUtil.exportExcelX(users, "测试导出表", "sheet1", UserExportVO.class, "测试导出表.xls", response);
    }
}
