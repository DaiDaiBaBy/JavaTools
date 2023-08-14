package com.zhoufu.springbootcsv;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : CsvWriter
 * @Author : ZhouFu
 * @Date: 2023/8/14 13:44
 * @Description : 导出CSV文件
 */
public class CsvWriter implements Closeable {

    private static final String COL_SEPARATOR = ",";
    private static final String RN = "\r\n";

    private String fileName;
    private final String[] titles;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private OutputStream os;

    public CsvWriter(String fileName, String[] titles,
                     HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.fileName = fileName;
        this.titles = titles;
        this.request = request;
        this.response = response;
        init();
    }

    private void init() throws IOException {
        // 读取字符编码
        String charset = "UTF-8";
        // 设置响应
        response.setCharacterEncoding(charset);
        response.setContentType("text/csv; charset=" + charset);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        final String userAgent = request.getHeader("USER-AGENT");
        try {
            if (StringUtils.contains(userAgent, "MSIE")) { // IE浏览器
                fileName = URLEncoder.encode(fileName, "UTF8");
            } else if (StringUtils.contains(userAgent, "Mozilla")) { // google， 火狐
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF8"); //其他；浏览器
            }
        } catch (UnsupportedEncodingException e) {
            // IGNORE
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        os = response.getOutputStream();
        writeRow(Arrays.asList(titles));
    }

    private void writeRow(List<?> row) throws IOException {
        StringBuilder rows = new StringBuilder();
        for (Object col : row) {
            rows.append(col == null ? "" : col).append(COL_SEPARATOR);
        }
        rows.deleteCharAt(rows.length() -1);
        rows.append(RN);
        doWrite(rows.toString());
    }

    private void doWrite(String title) throws IOException {
        os.write(title.getBytes("GBK"));
        os.flush();
    }

    @Override
    public void close() throws IOException {
        if (os != null) {
            os.close();
        }
    }


    // 调用write接口
    public void write(List<List<Object>> data) throws IOException {
        for (List<Object> row : data) {
            writeRow(row);
        }
    }
}
