package com.zhoufu.springbootasyncthreadpoolbatchlist.CodeGenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 *  MyBatis-Plus Code Generator
 *
 *  mybatis-plus代码生成器
 */
public class CodeGenerator {
    // 配置数据库信息
    private static final String BASE_PACKAGE = "com.zhoufu.springbootasyncthreadpoolbatchlist";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String[] INCLUDE_TABLES = {"target_table"};

//    // 项目根目录
//    private static final String PROJECT_ROOT_PATH = System.getProperty("user.dir");
//    private static final String BASE_PATH = PROJECT_ROOT_PATH + "/springboot-shardingsphere/src/main/java/com/zhoufu/springbootshardingsphere";
//    private static final String PARENT_PACKAGE_NAME = "com.zhoufu.springbootshardingsphere";
//
//    // 六个文件的路径
//    private static final String entityPath = BASE_PATH + "/entity";
//    private static final String mapperPath = BASE_PATH + "/mapper";
//    private static final String mapperXmlPath = PROJECT_ROOT_PATH + "/springboot-shardingsphere/src/main/resources/mapper";
//    private static final String servicePath = BASE_PATH + "/service";
//    private static final String serviceImplPath = BASE_PATH + "/service/impl";
//    private static final String controllerPath = BASE_PATH + "/controller";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/springboot-async-threadPool-batchList/src/main/java");
        gc.setAuthor("zf");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setIdType(IdType.AUTO);
        gc.setDateType(DateType.ONLY_DATE);

        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DB_URL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("!QAZ1qaz");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(BASE_PACKAGE);
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/springboot-async-threadPool-batchList/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setService(null);
//        templateConfig.setServiceImpl(null);
//        templateConfig.setController(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntitySerialVersionUID(false);
        strategy.setInclude(INCLUDE_TABLES);
        strategy.setTablePrefix("tb_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}