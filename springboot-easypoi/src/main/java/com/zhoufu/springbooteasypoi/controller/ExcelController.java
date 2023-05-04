package com.zhoufu.springbooteasypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.zhoufu.springbooteasypoi.domain.Person;
import com.zhoufu.springbooteasypoi.utils.ExcelUtils;
import com.zhoufu.springbooteasypoi.utils.utilSecond.TemplateExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName : ExcelController
 * @Author : ZhouFu
 * @Date: 2023/5/4 14:27
 * @Description : excel导出和 模板导出
 */
@Api(tags = "Excel模板导出")
@RestController
@RequestMapping("/excel")
@Slf4j
public class ExcelController {

    /**
     * 导出
     *
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "导出接口", notes = "导出接口")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setName("张三" + i);
            person.setUsername("张三" + i);
            person.setPhoneNumber("18888888888");
            person.setImageUrl("static/person.jpg");
            personList.add(person);
        }
        ExcelUtils.exportExcel(personList, "员工信息", "员工信息sheet", Person.class, "员工信息表", response);
    }

    /**
     * 导入
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "导入接口", notes = "导入接口")
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ApiImplicitParam(name = "文件", value = "file", dataType = "MultipartFile")
    public Object importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        long start = System.currentTimeMillis();
        List<Person> personList = ExcelUtils.importExcel(file, Person.class);
        log.info("导入excel所花时间：" + (System.currentTimeMillis() - start) + "'ms");
        return personList;
    }

    /**
     * 模板导出
     *
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "模板导出接口", notes = "模板导出接口")
    @RequestMapping(value = "/templateExport", method = RequestMethod.GET)
    public void templateExport(HttpServletResponse response) throws IOException {
        TemplateExportParams params = new TemplateExportParams("templates/templateMap.xlsx");
        /*--------------------------------------------------------------*/
        Map<String, Object> map = new HashMap<>(16);
        map.put("title", "全亚洲,最帅气人员名单");
        map.put("date", "2018-12-05");
        map.put("interviewer", "JustryDeng");
        List<Map<String, Object>> list = new ArrayList<>(16);
        Map<String, Object> tempMap;
        for (int i = 0; i < 5; i++) {
            tempMap = new HashMap<>();
            tempMap.put("name", "邓沙利文");
            tempMap.put("gender", new Random().nextInt(2) == 0 ? "男" : "女");
            tempMap.put("age", new Random().nextInt(90) + 11);
            tempMap.put("hobby", "活的，女的！！！");
            tempMap.put("handsomeValue", "100分(满分100分)");
            tempMap.put("motto", "之所以只帅到了全亚洲，是因为其他地方审美不同！");
            list.add(tempMap);
        }
        map.put("dataList", list);
        /*--------------------------------------------------------------*/
        // 生成workbook
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        TemplateExcelUtil.exportExcel("模板导出文件", response, workbook);
    }
}
