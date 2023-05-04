package com.zhoufu.springbooteasypoi.utils;

import com.aspose.cells.Workbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import lombok.extern.slf4j.Slf4j;
import com.aspose.cells.PdfSaveOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * @ClassName : MergePdfUtils
 * @Author : ZhouFu
 * @Date: 2023/5/4 16:59
 * @Description : pdf工具类
 */
@Slf4j
public class MergePdfUtils {

    /**
     * excel 转 pdf
     *
     * @param excelPath 文件路径
     * @param pdfPath   生成pdf路径
     */
    public static void excelConvertPdf(String excelPath, String pdfPath) {
        File file = new File(excelPath);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            Workbook workbook = new Workbook(fileInputStream);
            File pdfFile = new File(pdfPath);
            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            //把excel所有内容放在一张PDF 页面上；
            pdfSaveOptions.setOnePagePerSheet(false);
            //把excel所有表头放在一张pdf上
            pdfSaveOptions.setAllColumnsInOnePagePerSheet(true);
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            workbook.save(fileOS, pdfSaveOptions);
            fileInputStream.close();
            fileOS.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 合并pdf
     * @param sourceFileList  待合并文件  多个pdf文件路径数组
     * @param destinationPath 合并文件输出地址
     */
    public static void mergePdf(String[] sourceFileList, String destinationPath) {
        try {
            Document document = new Document();
            FileOutputStream fos = new FileOutputStream(destinationPath);
            PdfCopy copy = new PdfCopy(document, fos);
            document.open();
            ArrayList<PdfReader> readerList = new ArrayList<com.itextpdf.text.pdf.PdfReader>();
            int totalPages = 0;
            for (String file : sourceFileList) {
                PdfReader reader = new PdfReader(file);
                readerList.add(reader);
                totalPages += reader.getNumberOfPages();
            }
            for (PdfReader reader : readerList) {
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    copy.addPage(copy.getImportedPage(reader, i));
                }
            }
            document.close();
            log.info("成功合并：" + sourceFileList.length + "个PDF文件，共计" + totalPages + "页。");
        } catch (Exception e) {
            log.error("合并失败：" +e.getMessage());
            e.printStackTrace();
        }
    }
}
