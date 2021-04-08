package com.dreamwolf.video;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.dreamwolf.video.mapper.UserMapper;
import com.dreamwolf.video.mapper.UserMsgsMapper;
import com.dreamwolf.video.pojo.User;
import com.dreamwolf.video.pojo.UserMsgs;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@MapperScan("com.dreamwolf.video.*")
class VideoApplicationTests {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMsgsMapper userMsgsMapper;

    @Test
    void contextLoads() {

        System.out.println("1111111111");
        List<User> userList =userMapper.selectList(null);
        userList.forEach(System.out::println);

//        List<UserMsgs> userList =userMsgsMapper.selectList(null);
//        userList.forEach(System.out::println);

    }

    //代码生成器
    @Test
    void testCode(){
        //需要构建一个代码自动生成器
        AutoGenerator mpg = new AutoGenerator();
        //配置策略
        //1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取当前用户的路径
        String projectPath = System.getProperty("user.dir");
        //输出目录
        gc.setOutputDir(projectPath+"/src/main/java");
        //设置作者
        gc.setAuthor("老徐");
        //是否打开资源管理器
        gc.setOpen(false);
        //是否覆盖原来生成的代码
        gc.setFileOverride(false);
        //服务的名字
        gc.setServiceName("%sService");     //去除service的 I 前缀

        //主键生成策略（默认）
        gc.setIdType(IdType.ID_WORKER);
        //设置日期类型（普通类型）
        gc.setDateType(DateType.ONLY_DATE);

        //配置Swagger文档
        gc.setSwagger2(true);
        //将全局配置放到代码生成器里面
        mpg.setGlobalConfig(gc);


        //2. 设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/BiliBili_db");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);


        //3. 包的配置
        PackageConfig pc = new PackageConfig();
        //设置模块的名字
        pc.setModuleName("video");
        //放在哪个包下面
        pc.setParent("com.dreamwolf");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);




        //4. 策略配置
        StrategyConfig sgc = new StrategyConfig();
//        sgc.setInclude("user");     //设置要映射的表名，可以放多个表名，根据这个名字，自动生成对应表的模块结构
//        sgc.setInclude("usermsgs");
//        sgc.setInclude("zoning");
//        sgc.setInclude("video");
//        sgc.setInclude("videodata");
//        sgc.setInclude("videorating");
//        sgc.setInclude("videolike");
//        sgc.setInclude("videofavorite");
//        sgc.setInclude("userfavoritelist");
//        sgc.setInclude("videocomment");
        sgc.setInclude("comment");
        // 下划线转驼峰命名
        sgc.setNaming(NamingStrategy.underline_to_camel);   //包的命名规则
        sgc.setColumnNaming(NamingStrategy.underline_to_camel); //列的命名规则
        //sgc.setSuperEntityClass("你自己的父类实体，没有就不用设置");
        //是否使用lombok开启注解
        sgc.setEntityLombokModel(true);
        //逻辑删除
        sgc.setLogicDeleteFieldName("deleted");
        //自动填充，创建时间，更新时间
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtUpdate = new TableFill("gmt_update", FieldFill.INSERT_UPDATE);
        //创建list结合来保存 “创建时间” 和 “更新时间” 的策略
        ArrayList<TableFill> tablefills = new ArrayList<>();
        tablefills.add(gmtCreate);
        tablefills.add(gmtUpdate);
        //把表的自动填充策略设置到sgc
        sgc.setTableFillList(tablefills);
        //乐观锁
        sgc.setVersionFieldName("version");
        //开启RestFul的驼峰命名
        sgc.setRestControllerStyle(true);

        //请求映射的命名样式，用下划线拼接，比如：localhost:8080/hello_id_2
        sgc.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(sgc);

        //执行
        mpg.execute();
    }

}
