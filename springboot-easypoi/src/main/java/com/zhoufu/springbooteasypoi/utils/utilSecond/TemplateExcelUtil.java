package com.zhoufu.springbooteasypoi.utils.utilSecond;

import com.zhoufu.springbooteasypoi.common.ExcelTypeEnum;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
/**
 * @ClassName : TemplateExcelUtil
 * @Author : ZhouFu
 * @Date: 2023/5/4 14:02
 * @Description : 模版导出
 */
public class TemplateExcelUtil {

    /**
     *
     * @param fileName 文件名称
     * @param response
     * @param workbook excel数据
     * @throws IOException
     */
    public static void exportExcel(String fileName, HttpServletResponse response, Workbook workbook)
            throws IOException {
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * 下载
     *
     * @param fileName 文件名称
     * @param response
     * @param workbook excel数据
     */
    // 通过模板导只能以xls为结尾,xlsx为结尾时不能正常打开
    // 不用模板xls,xlsx都能正常打开,通过ExcelType设置,ExcelType.HSSF:xls ExcelType.XSSF:xlsx
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook)
            throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName + "." + ExcelTypeEnum.XLS.getValue(), "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
