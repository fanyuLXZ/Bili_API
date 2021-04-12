package com.dreamwolf.dynamic;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

//代码自动生成器
@SpringBootTest
public class Automatically {

    @Test
    public void Automatice(){
        //构建一个 代码自动生成器对象
        AutoGenerator mpg = new AutoGenerator();

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("userdynamic","dynamicdata","dynamiclike","dynamiccomment");//userDynamic  dynamicData  dynamicLike  dynamicComment
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("deleted");//逻辑删除配置
        //自动填充配置
        TableFill gmtCreate=new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified=new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills=new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        //乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);//localhost:8080/heleo_id_2
        mpg.setStrategy(strategy);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取用户路径
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zhaolin");
        gc.setOpen(false); //是否打开资源管理器
        //gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        //设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/bilibili_db?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true");
        //dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);//驱动类型
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("business");
        pc.setParent("com.dreamwolf.dynamic");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("Controller");
        mpg.setPackageInfo(pc);

        mpg.execute();//执行
    }

}
