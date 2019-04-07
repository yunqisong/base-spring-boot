package com.wosummer.generator;

import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;

import static cn.hutool.system.SystemUtil.USER_DIR;
import static cn.hutool.system.SystemUtil.USER_NAME;

/**
 * @author yunqisong
 * @date 2018/9/30
 * FOR: TODO:
 */

@Slf4j
@SuppressWarnings("ALL")
public class GenneratorTask extends DefaultTask {


  public static final String SPRING_DATASOURCE = "spring.datasource.";

  @TaskAction
  public void gennerator() throws IOException {
    System.out.println("创建文件根目录:" + SystemUtil.get(USER_DIR));

    String ymlPath = SystemUtil.get(USER_DIR) + "/src/main/resources/application-dev.yml";

    YamlKit.use(ymlPath);

    GeneratorProperties generatorPropertie = new GeneratorProperties()
      .setDriverName(YamlKit.getStr(SPRING_DATASOURCE + "driver-class-name"))
      .setUrl(YamlKit.getStr(SPRING_DATASOURCE + "url"))
      .setUsername(YamlKit.getStr(SPRING_DATASOURCE + "username"))
      .setPassword(YamlKit.getStr(SPRING_DATASOURCE + "password"))
      .setBasePackage(YamlKit.getStr(SPRING_DATASOURCE + "base-package"))
      .setPrefix(YamlKit.getStr(SPRING_DATASOURCE + "table-prefix"))
      .setTables(YamlKit.getStr(SPRING_DATASOURCE + "tables"))
      .setIdType(YamlKit.getStr(SPRING_DATASOURCE + "id-type"))
      .setAuthor(SystemUtil.get(USER_NAME))
      .setPath(SystemUtil.get(USER_DIR) + "/");

    System.out.println(JSONUtil.toJsonPrettyStr(generatorPropertie));

    CodeGenerator.execute(generatorPropertie);

  }
}

