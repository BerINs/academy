package io.peaches.academy.service.edu;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import io.peaches.academy.service.base.model.BaseEntity;

import java.util.Collections;

public class FastAutoGeneratorTest {
    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/academy","root","xiongyuhang")
                .globalConfig(builder -> {
                    builder.outputDir(System.getProperty("user.dir")+"/academy_service/service_edu/src/main/java")    //输出路径(写到java目录)
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .dateType(DateType.TIME_PACK)
                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("io.peaches.academy.service")
                            .moduleName("edu")
                            .entity("entity")
                            .service("service")
                            .controller("controller")
                            .mapper("mapper")
                            .serviceImpl("serviceImpl")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir")+"/academy_service/service_edu/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder .addTablePrefix("edu_")

                            .serviceBuilder()
                            .formatServiceFileName("%sService")

                            .entityBuilder()
                            .superClass(BaseEntity.class)
                            .addSuperEntityColumns("id", "gmt_create", "gmt_modified")
                            .addTableFills(new Column("gmt_create", FieldFill.INSERT))
                            .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE))
                            .enableLombok()
                            .versionColumnName("version")
                            .idType(IdType.ASSIGN_ID)
                            .logicDeleteColumnName("is_deleted")
                            .enableTableFieldAnnotation()
                            .enableRemoveIsPrefix()


                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle();

//                            .mapperBuilder()
//                            .enableBaseResultMap()  //生成通用的resultMap
//                            .superClass(BaseMapper.class)
//                            .formatMapperFileName("%sMapper")
//                            .enableMapperAnnotation();
                })
                .execute();
    }
}


