package com.dreamwolf.imageresource;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.dreamwolf.entity.video.Video;
import com.dreamwolf.video.mapper.VideoMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@MapperScan("com.dreamwolf.video.*")
class VideoApplicationTests {

    @Resource
    public VideoMapper videoMapper;


    @Test
    void show() {
        Integer[] arr = new Integer[]{1,9,8,7,2,6};
        int temp=0;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]<arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        for (Integer a : arr){
            System.out.println(a);
        }

    }

    @Test
    void videoUtil4(){
        Map map = new HashMap();
        List<Video> list = videoMapper.selectList(null);
        Map maplist = new HashMap();
        for(Video videolist : list){
            maplist.put("bvID",videolist.getBvID());    //bv号
            maplist.put("uID",videolist.getUID());      //视频作者ID
            maplist.put("bvCoverImgPath",videolist.getBvCoverImgPath());    //视频封面图
            maplist.put("bvVideoPath",videolist.getBvVideoPath());  //视频文件路径
            maplist.put("bvTitle",videolist.getBvTitle());     //视频标题
            maplist.put("bvDesc",videolist.getBvDesc());    //视频简介
            maplist.put("bvPostTime",videolist.getBvPostTime());    //视频上传日期（默认为当前时间）
            maplist.put("bvChildZoning",videolist.getBvChildZoning());  //视频子分区ID
            maplist.put("bvIsDel",videolist.getBvIsDel());  //是否已删除，0为未删除，1为已删除
        }
        map.put("data",maplist);
        for (Object key : maplist.keySet()){
            System.out.println(key);
        }
    }


    //代码生成器
    @Test
    void contextLoads() {
        //需要构建一个代码自动生成器
        AutoGenerator mpg = new AutoGenerator();
        //配置策略 //1. 全局配置
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
        gc.setServiceName("%sService");
        //去除service的 I 前缀 //主键生成策略（默认）
        gc.setIdType(IdType.ID_WORKER);
        //设置日期类型（普通类型）
        gc.setDateType(DateType.ONLY_DATE);
        //配置Swagger文档
        gc.setSwagger2(true);
        // 将全局配置放到代码生成器里面
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


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);


        //4. 策略配置
        StrategyConfig sgc = new StrategyConfig();
        sgc.setInclude(new String[] { "video","videocomment","videodata","videofavorite","videolike","videorating","zoning","user" });
        // 设置要映射的表名，可以放多个表名，根据这个名 字，自动生成对应表的模块结构
        // 下划线转驼峰命名
        sgc.setNaming(NamingStrategy.underline_to_camel);// 包的命名规则
        sgc.setColumnNaming(NamingStrategy.underline_to_camel);// 列的命名规则
//         sgc.setSuperEntityClass("你自己的父类实体，没有就不用设置");
        // 是否使用lombok开启注解
        sgc.setEntityLombokModel(true);
        // 逻辑删除
        sgc.setLogicDeleteFieldName("deleted");
        // 自动填充，创建时间，更新时间
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtUpdate = new TableFill("gmt_update", FieldFill.INSERT_UPDATE);
        // 创建list结合来保存 “创建时间” 和 “更新时间” 的策略
        ArrayList<TableFill> tablefills = new ArrayList<>();
        tablefills.add(gmtCreate); tablefills.add(gmtUpdate);
        // 把表的自动填充策略设置到sgc
        sgc.setTableFillList(tablefills);
        // 乐观锁
        sgc.setVersionFieldName("version");
        // 开启RestFul的驼峰命名
        sgc.setRestControllerStyle(true);
        // 请求映射的命名样式，用下划线拼接，比如：localhost:8080/hello_id_2
        sgc.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(sgc);
        // 执行
        mpg.execute();
    }

}
