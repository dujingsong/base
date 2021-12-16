package cn.imadc.application.base.codegenerator.generator;

import cn.imadc.application.base.codegenerator.constant.FastAutoGeneratorConfig;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BaseFastAutoGenerator {

    public void generate(String dbUrl, String dbUserName, String dbPassword, String outputDir, String tableName, String packageName, String moduleName, String author) {
        Map<OutputFile, String> pathInfoMap = new HashMap<>();
        for (OutputFile outputFile : OutputFile.values()) {
            if (outputFile.equals(OutputFile.other)) {
                continue;
            }
            if (outputFile.equals(OutputFile.serviceImpl)) {
                pathInfoMap.put(outputFile, outputDir + File.separatorChar + OutputFile.service.name()
                        + File.separator + FastAutoGeneratorConfig.SERVICE_IMPL_PACKAGE);
                continue;
            }
            if (outputFile.equals(OutputFile.mapperXml)) {
                pathInfoMap.put(outputFile, outputDir);
                continue;
            }
            pathInfoMap.put(outputFile, outputDir + File.separatorChar + outputFile.name());
        }

        FastAutoGenerator.create(dbUrl, dbUserName, dbPassword)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir) // 指定输出目录
                    ;
                })
                .packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(pathInfoMap)
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .mapperBuilder().enableMapperAnnotation()// 添加@Mapper注解
                            .entityBuilder().enableLombok()// 实体是用lombok代替get set方法
                            .controllerBuilder().enableRestStyle()// controller中是用@restController注解
                    ;
                })
                .templateConfig(builder -> {
                    builder.service(FastAutoGeneratorConfig.SERVICE_TML)
                            .serviceImpl(FastAutoGeneratorConfig.SERVICE_IMPL_TML)
                            .controller(FastAutoGeneratorConfig.CONTROLLER_TML)
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
