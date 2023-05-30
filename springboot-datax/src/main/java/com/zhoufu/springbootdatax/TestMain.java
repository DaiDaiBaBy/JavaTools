package com.zhoufu.springbootdatax;
import com.alibaba.datax.core.Engine;
/**
 * dataX测试代码，   源表 数据迁移到 目标表
 *  单表 mysql操作
 */
public class TestMain {
    public static String getCurrentClasspath(){
        //需要指定dataX的home目录
        //指定运行的参数
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String currentClasspath = classLoader.getResource("").getPath();
        // 当前操作系统
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Win")) {
            // 删除path中最前面的/
            currentClasspath = currentClasspath.substring(1, currentClasspath.length()-1);
        }
        return currentClasspath;
    }
    public static void main(String[] args) {
        String fileName = getCurrentClasspath()+ "/configuration/job.json";
        System.out.println(fileName);
        System.setProperty("datax.home","D:\\workspace\\develop\\JavaTools\\springboot-datax\\datax");
        String[] datxArgs2 = {  "-job", getCurrentClasspath()+ "/configuration/job.json","-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
