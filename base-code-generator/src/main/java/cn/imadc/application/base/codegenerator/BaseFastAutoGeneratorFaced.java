package cn.imadc.application.base.codegenerator;

import cn.imadc.application.base.codegenerator.generator.BaseFastAutoGenerator;

public class BaseFastAutoGeneratorFaced {

    public static void generate(String dbUrl, String dbUserName, String dbPassword, String outputDir, String tableName,
                                String packageName, String moduleName, String author) {
        BaseFastAutoGenerator baseFastAutoGenerator = new BaseFastAutoGenerator();

        baseFastAutoGenerator.generate(dbUrl, dbUserName, dbPassword, outputDir, tableName, packageName, moduleName,
                author);
    }
}
