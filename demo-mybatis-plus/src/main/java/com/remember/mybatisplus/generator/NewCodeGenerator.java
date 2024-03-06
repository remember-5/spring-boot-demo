/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MybatisPlus 新代码生成器
 *
 * @author wangjiahao
 * @date 2024/3/6 10:03
 */
public class NewCodeGenerator {
    /** 数据库信息 */
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/system?remarks=true&useInformationSchema=true";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "123456";
    public static final String DB_SCHEMA = "";

    /** 基本配置 */
    public static final String AUTHOR = "wangjiahao";
    public static final String ANNOTATION_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 保存路径 */
    public static final String OUTPUT_DIR = "/Users/wangjiahao/IdeaProjects/spring-boot-demo/demo-mybatis-plus";
    /** 生成表, 可生成多个 */
    public static final List<String> TABLES = Arrays.asList("sys_user");
    /** 忽略的表前缀 */
    public static final List<String> TABLES_PREFIX = Arrays.asList("t_", "c_", "sys_");
    /** 包名 */
    public static final String PACKAGE_PATH = "com.remember.mybatisplus.test";
    /** 模块名称 */
    public static final String MODULE_NAME = "system";
    /** xml存放包名 */
    public static final String MAPPER_XML_PATH = "mapper";

    /** 模版路径 */
    public static final String CONTROLLER_TEMPLATE_PATH = "generator/templates/controller.java";
    public static final String ENTITY_TEMPLATE_PATH = "generator/templates/entity.java";
    public static final String MAPPER_TEMPLATE_PATH = "generator/templates/mapper.java";
    public static final String MAPPER_XML_TEMPLATE_PATH = "generator/templates/mapper.xml";
    public static final String SERVICE_TEMPLATE_PATH = "generator/templates/service.java";
    public static final String SERVICE_IMPL_TEMPLATE_PATH = "generator/templates/serviceImpl.java";
    public static final String DTO_TEMPLATE_PATH = "generator/templates/entityDTO.java.ftl";
    public static final String VO_TEMPLATE_PATH = "generator/templates/entityVO.java.ftl";


    public static void main(String[] args) {
        // 使用元数据查询的方式生成代码,默认已经根据jdbcType来适配java类型,支持使用typeConvertHandler来转换需要映射的类型映射
        final DataSourceConfig.Builder db = new DataSourceConfig.Builder(DB_URL, DB_USERNAME, DB_PASSWORD)
                .schema(DB_SCHEMA)
                .keyWordsHandler(new MySqlKeyWordsHandler());
        FastAutoGenerator
                // 数据源配置
                .create(db)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
                            .enableSpringdoc() // 开启 SpringDoc 模式
                            .disableOpenDir() //禁止打开输出目录
                            .commentDate(ANNOTATION_DATE_FORMAT)  //注释日期
                            .outputDir(OUTPUT_DIR + "/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(PACKAGE_PATH) // 设置父包名
                            .moduleName(MODULE_NAME) // 设置父包模块名
                            // .xml("") // 这个配置 会在java下生成，需要用下面的配置
                            .pathInfo(Collections.singletonMap(OutputFile.xml, OUTPUT_DIR + "/src/main/resources/" + MAPPER_XML_PATH)); // 设置mapperXml生成路径
                })
                // 注入配置
                .injectionConfig(consumer -> {
                    // DTO
                    consumer.customFile(customFile -> {
                        customFile.fileName("DTO.java")
                                .packageName("dto")
                                .templatePath(DTO_TEMPLATE_PATH);
                    });
                    // VO
                    consumer.customFile(customFile -> {
                        customFile.fileName("VO.java")
                                .packageName("vo")
                                .templatePath(VO_TEMPLATE_PATH);
                    });
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES) // 设置需要生成的表名
                            .addTablePrefix(TABLES_PREFIX) // 设置过滤表前缀

                            // entity 策略配置
                            .entityBuilder()
                            .enableLombok()
                            .enableFileOverride() // 覆盖已生成文件
                            .logicDeleteColumnName("is_deleted") //逻辑删除字段名
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE)
                            )   //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                            .enableChainModel() // 开启链式模型
                            .enableTableFieldAnnotation()       // 开启生成实体时生成字段注解

                            // mapper 策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)   //设置父类
                            .formatMapperFileName("%sMapper")   //格式化 mapper 文件名称
                            .enableMapperAnnotation()       //开启 @Mapper 注解
                            .formatXmlFileName("%sXml") //格式化 Xml 文件名称 如 UserXml

                            // service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // 如:UserService
                            .formatServiceImplFileName("%sServiceImpl") // 如:UserServiceImpl

                            // controller 策略配置
                            .controllerBuilder()
                            .formatFileName("%sController") // 如 UserController
                            .enableRestStyle();  //开启生成 @RestController 控制器

                })
                .templateConfig(builder -> {
                    builder.controller(CONTROLLER_TEMPLATE_PATH)
                            .entity(ENTITY_TEMPLATE_PATH)
                            .mapper(MAPPER_TEMPLATE_PATH)
                            .xml(MAPPER_XML_TEMPLATE_PATH)
                            .service(SERVICE_TEMPLATE_PATH)
                            .serviceImpl(SERVICE_IMPL_TEMPLATE_PATH);
                })

                // 模板配置
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new EnhanceFreemarkerTemplateEngine())
                // 执行
                .execute();
    }

}
