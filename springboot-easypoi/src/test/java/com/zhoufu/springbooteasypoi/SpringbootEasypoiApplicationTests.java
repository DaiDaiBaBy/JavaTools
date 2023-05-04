package com.zhoufu.springbooteasypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.date.DateUtil;
import com.zhoufu.springbooteasypoi.domain.HandsomeBoy;
import com.zhoufu.springbooteasypoi.domain.InterviewResult;
import com.zhoufu.springbooteasypoi.domain.Student;
import com.zhoufu.springbooteasypoi.utils.MergePdfUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@SpringBootTest
@Slf4j
public class SpringbootEasypoiApplicationTests {

    @Test
    public void contextLoads() {
    }

    // easyPoi多表头
    @Test
    public void dynaCol() {
        try {
            List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
            ExcelExportEntity colEntity = new ExcelExportEntity("商品名称", "title");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            colEntity = new ExcelExportEntity("供应商", "supplier");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            ExcelExportEntity taobaoColGroup = new ExcelExportEntity("淘宝", "taobao");
            List<ExcelExportEntity> taobaoColList = new ArrayList<ExcelExportEntity>();
            taobaoColList.add(new ExcelExportEntity("市场价", "orgPrice", 20));
            taobaoColList.add(new ExcelExportEntity("专区价", "salePrice", 20));
            taobaoColGroup.setList(taobaoColList);
            colList.add(taobaoColGroup);

            ExcelExportEntity tainmaoColGroup = new ExcelExportEntity("天猫", "tianmao");
            List<ExcelExportEntity> tianmaoColList = new ArrayList<ExcelExportEntity>();
            tianmaoColList.add(new ExcelExportEntity("市场价", "orgPrice", 20));// 20：表格宽度
            tianmaoColList.add(new ExcelExportEntity("专区价", "salePrice", 20));
            ExcelExportEntity imageEntity = new ExcelExportEntity("商品图片", "image");
            // type设置导出类型 1是文本, 2是图片, 3是函数, 10是数字  默认是文本
            imageEntity.setType(2);// 图片
            imageEntity.setExportImageType(1);// 图片的类型,1是文件,2是数据库
            imageEntity.setWidth(10);// 宽度
            imageEntity.setHeight(20);// 高度
            tianmaoColList.add(imageEntity);
            tainmaoColGroup.setList(tianmaoColList);
            colList.add(tainmaoColGroup);

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < 10; i++) {
                Map<String, Object> valMap = new HashMap<String, Object>();
                valMap.put("title", "名称." + i);
                valMap.put("supplier", "供应商." + i);

                List<Map<String, Object>> taobaoDetailList = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < 2; j++) {
                    Map<String, Object> taobaoValMap = new HashMap<String, Object>();
                    taobaoValMap.put("orgPrice", "淘宝.市场价." + j);
                    taobaoValMap.put("salePrice", "淘宝.专区价." + j);
                    taobaoDetailList.add(taobaoValMap);
                }
                valMap.put("taobao", taobaoDetailList);

                List<Map<String, Object>> tianmaoDetailList = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < 3; j++) {
                    Map<String, Object> tianmaoValMap = new HashMap<String, Object>();
                    tianmaoValMap.put("orgPrice", "天猫.市场价." + j);
                    tianmaoValMap.put("salePrice", "天猫.专区价." + j);
                    tianmaoValMap.put("image", "https://img0.baidu.com/it/u=1115130400,112519339&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=508");
                    tianmaoDetailList.add(tianmaoValMap);
                }
                valMap.put("tianmao", tianmaoDetailList);

                list.add(valMap);
            }

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("价格分析表", "数据"), colList, list);
            FileOutputStream fos = new FileOutputStream("D:/workspace/develop/JavaTools/springboot-easypoi/doc/价格分析表.xlsx");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            log.error("Excel导出多表头异常--File：" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Excel导出多表头异常--IO：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 直接导出(无需模板) 注:此方式存在一些不足之处，在对性能、excel要求比较严格时不推荐使用
     *
     * @throws IOException
     */
    @Test
    public void directExportExcel() throws IOException {
        // Map作为每一行的数据容器，List作为行的容器
        List<Map<String, Object>> rowDataList = new ArrayList<>();
        // 每个ExcelExportEntity存放Map行数据的key
        List<ExcelExportEntity> keyList = new ArrayList<>();
        Map<String, Object> aRowMap;
        final int COMMON_KEY_INDEX = 10;
        for (int i = 0; i < 5; i++) {
            // 一个Map对应一行数据（如果需要导出多行数据，那么需要多个Map）
            aRowMap = new HashMap<>(16);
            for (int j = 0; j < COMMON_KEY_INDEX; j++) {
                String key = j + "";
                aRowMap.put(key, "坐标(" + i + "," + j + ")");
            }
            rowDataList.add(aRowMap);
            // 同一列对应的cell,在从Map里面取值时，会共用同一个key
            // 因此ExcelExportEntity的个数要保持和列数做多的行 的map.size()大小一致
            if (i == 0) {
                ExcelExportEntity excelExportEntity;
                for (int j = 0; j < COMMON_KEY_INDEX; j++) {
                    excelExportEntity = new ExcelExportEntity();
                    excelExportEntity.setKey(j + "");
                    // 设置cell宽
                    excelExportEntity.setWidth(15D);
                    // 设置cell是否自动换行
                    excelExportEntity.setWrap(true);
                    keyList.add(excelExportEntity);
                }
            }
        }
        // excel总体设置
        ExportParams exportParams = new ExportParams();
        // 不需要标题
        exportParams.setCreateHeadRows(false);
        // 指定sheet名字
        exportParams.setSheetName("直接导出数据测试");
        // 生成workbook 并导出
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, keyList, rowDataList);
        File savefile = new File("D:\\workspace\\develop\\JavaTools\\springboot-easypoi\\doc");
        if (!savefile.exists()) {
            boolean result = savefile.mkdirs();
            System.out.println("目录不存在，创建" + (result ? "成功!" : "失败！"));
        }
        FileOutputStream fos = new FileOutputStream("D:\\workspace\\develop\\JavaTools\\springboot-easypoi\\doc\\坐标.xls");
        workbook.write(fos);
        fos.close();
    }

    /**
     * 对象---直接导出(无需模板) 注:如果模型 的父类的属性也有@Excel注解，那么导出excel时，会连该模型的父类的属性也一会儿导出
     *
     * @throws IOException
     */
    @Test
    public void directExportExcelByObject() throws IOException {
        List<Student> list = new ArrayList<>(16);
        Student student;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            student = new Student(i + "", "name" + i, random.nextInt(2), random.nextInt(100), new Date(),
                    "className" + i);
            student.setSchoolName("学校名称" + i);
            student.setSchoolAddress("学校地址" + i);
            list.add(student);
        }
        ExportParams exportParams = new ExportParams();
        exportParams.setSheetName("我是sheet名字");
        // 生成workbook 并导出
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Student.class, list);
        File savefile = new File("D:/workspace/develop/JavaTools/springboot-easypoi/doc");
        if (!savefile.exists()) {
            boolean result = savefile.mkdirs();
            System.out.println("目录不存在，创建" + (result ? "成功!" : "失败！"));
        }
        FileOutputStream fos = new FileOutputStream("D:/workspace/develop/JavaTools/springboot-easypoi/doc/学生.xls");
        workbook.write(fos);
        fos.close();
    }

    // 模板导出---Map组装数据
    // 注:.xls的模板可以导出.xls文件，也可以导出xlsx的文件;同样的, .xlsx的模板可以导出.xls文件，也可以导出xlsx的文件;
    @Test
    public void templateExportExcelByMap() throws IOException {
        // 加载模板
        TemplateExportParams params = new TemplateExportParams("templates/templateMap.xls");
        Map<String, Object> map = new HashMap<>(16);
        map.put("title", "全亚洲,最帅气人员名单");
        map.put("date", DateUtil.date().toString());
        map.put("interviewer", "JustryDeng");
        List<Map<String, Object>> list = new ArrayList<>(16);
        Map<String, Object> tempMap;
        for (int i = 0; i < 5; i++) {
            tempMap = new HashMap<>();
            tempMap.put("name", "邓沙利"+ i);
            tempMap.put("gender", new Random().nextInt(2) == 0 ? "男" : "女");
            tempMap.put("age", new Random().nextInt(90) + 11);
            tempMap.put("hobby", "活的，女的！！！");
            tempMap.put("handsomeValue", "100分(满分100分)");
            tempMap.put("motto", "之所以只帅到了全亚洲，是因为其他地方审美不同！");
            list.add(tempMap);
        }
        map.put("dataList", list);
        // 生成workbook 并导出
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/workspace/develop/JavaTools/springboot-easypoi/doc");
        if (!savefile.exists()) {
            boolean result = savefile.mkdirs();
            System.out.println("目录不存在,进行创建,创建" + (result ? "成功!" : "失败！"));
        }
        FileOutputStream fos = new FileOutputStream("D:/workspace/develop/JavaTools/springboot-easypoi/doc/采访结果.xls");
        workbook.write(fos);
        fos.close();
    }

    // 模板导出---对象组装数据
    // 注:实际上仍然是"模板导出---Map组装数据",不过这里借助了工具类，将对象先转换为了Map<String, Object>
    // 注:.xls的模板可以导出.xls文件，也可以导出xlsx的文件;同样的, .xlsx的模板可以导出.xls文件，也可以导出xlsx的文件;
    @Test
    public void templateExportExcelByObject() throws IOException, IllegalAccessException {
        // 加载模板
        TemplateExportParams params = new TemplateExportParams("templates/templateObject.xlsx");
        // 组装数据
        InterviewResult interviewResult = new InterviewResult();
        interviewResult.setTitle("全亚洲最帅人员名单");
        interviewResult.setInterviewer("邓沙利文");
        interviewResult.setDate(DateUtil.date().toString());
        List<HandsomeBoy> list = new ArrayList<>(8);
        interviewResult.setList(list);
        HandsomeBoy handsomeBoy;
        for (int i = 0; i < 5; i++) {
            handsomeBoy = new HandsomeBoy();
            handsomeBoy.setAge(20 + i);
            handsomeBoy.setGender(i % 2 == 0 ? "女" : "男");
            handsomeBoy.setHandsomeValue(95 + i + "(满分100分)");
            handsomeBoy.setHobby("女。。。。");
            handsomeBoy.setMotto("我是一只小小小小鸟~");
            handsomeBoy.setName("JustryDeng");
            list.add(handsomeBoy);
        }
        // 生成workbook 并导出
        Workbook workbook = ExcelExportUtil.exportExcel(params, objectToMap(interviewResult));
        File savefile = new File("D:/workspace/develop/JavaTools/springboot-easypoi/doc");
        if (!savefile.exists()) {
            boolean result = savefile.mkdirs();
            System.out.println("目录不存在,进行创建,创建" + (result ? "成功!" : "失败！"));
        }
        FileOutputStream fos = new FileOutputStream("D:/workspace/develop/JavaTools/springboot-easypoi/doc/采访结果.xlsx");
        workbook.write(fos);
        fos.close();
    }

    /**
     * 对象转换为Map<String, Object>的工具类
     *
     * @param obj 要转换的对象
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>(16);
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }


    @Test
    public void ExcelPdfTest(){
        String excelPath = "D:\\workspace\\develop\\JavaTools\\springboot-easypoi\\doc";
//        String excelPath = "D:\\workspace\\develop\\JavaTools\\springboot-easypoi\\src\\main\\resources\\templates";
        String pdfPath = "D:\\workspace\\develop\\JavaTools\\springboot-easypoi\\doc\\pdf\\";
        File dir = new File(excelPath);
        File[] file = dir.listFiles();
        List<String> files = new ArrayList<>();
        for (File file1 : file) {
            if (!file1.isDirectory()) {
                // 排除文件夹
                files.add(file1.toString());
            }
        }
        if (CollectionUtils.isNotEmpty(files)) {
            String[] fileStr = files.toArray(new String[files.size()]);
            // 生成pdf
            for (int i = 0; i < fileStr.length; i++) {
                MergePdfUtils.excelConvertPdf(fileStr[i], pdfPath+  System.currentTimeMillis()+ ".pdf");
            }
        }
    }
}
