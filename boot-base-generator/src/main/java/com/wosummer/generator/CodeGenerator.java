package com.wosummer.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @author yunqisong
 * @date 2018/9/28
 * FOR: 重写MybatisPlus提供的代码生成器
 */

@Slf4j
@SuppressWarnings("ALL")
@AllArgsConstructor
public class CodeGenerator extends AutoGenerator {

  private GeneratorProperties generatorProperties;

  /**
   * 数据源配置
   *
   * @return
   */
  public DataSourceConfig dataSourceConfig() {

    return new DataSourceConfig()
      .setDriverName(generatorProperties.getDriverName())
      .setPassword(generatorProperties.getPassword())
      .setUsername(generatorProperties.getUsername())
      .setUrl(generatorProperties.getUrl())
      ;
  }

  /**
   * 模板配置
   *
   * @return
   */
  public TemplateConfig templateConfig() {

    return new TemplateConfig()
      // 采用自定义Swagger规范的Model模板
      .setEntity("/" + (generatorProperties.getDriverName().contains("oracle") ? "templateOracle" : "template") + "/myModel.java.vm")
      // 建造模式还是更新模式
//            .setMapper(ConstVal.TEMPLATE_MAPPER)
      .setXml(null)
      .setController(null)
      .setService(null)
      .setServiceImpl(null);
  }

  /**
   * 数据表配置
   *
   * @return
   */
  public StrategyConfig strategyConfig() {

    return new StrategyConfig()
      .setTablePrefix(generatorProperties.getPrefix())
      // 此处可以修改为您的表前缀
      .setNaming(NamingStrategy.underline_to_camel)
      // 表名生成策略
      .setInclude(generatorProperties.getTables().split(","))
      // 需要生成的表
      .setRestControllerStyle(true)
      .setTableFillList(Collections.singletonList(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE)))
      .setEntityBuilderModel(true)
      .setEntityLombokModel(true)
      .setEntityColumnConstant(true);
  }

  /**
   * 全局配置
   *
   * @return
   */
  public GlobalConfig globalConfig() {

    return new GlobalConfig()
      .setDateType(DateType.ONLY_DATE)
      .setIdType(StrUtil.isBlank(generatorProperties.getIdType()) ? IdType.AUTO : IdType.valueOf(generatorProperties.getIdType()))
      // 生成ID的策略
      .setOutputDir(generatorProperties.getPath() + "src/main/java")
      // 输出目录
      .setAuthor(generatorProperties.getAuthor())
      // 作者
      .setFileOverride(true)
      // 是否覆盖文件
      .setActiveRecord(false)
      // 关闭 activeRecord 模式
      .setEnableCache(false)
      // XML 二级缓存
      .setBaseResultMap(true)
      // XML ResultMap
      .setBaseColumnList(true)
      // XML columList
      .setSwagger2(true)
      // Swagger2支持
      .setOpen(false)
      // 生成后打开文件夹
      .setMapperName("%sMapper")
      // 自定义文件命名，注意 %s 会自动填充表实体属性！
      .setXmlName("%sMapper")
      .setServiceName("%sService")
      .setServiceImplName("%sServiceImpl")
      .setControllerName("%sController");
  }

  /**
   * 包配置
   *
   * @return
   */
  public PackageConfig packageConfig() {

    return new PackageConfig()
      .setEntity("entity")
      .setParent(generatorProperties.getBasePackage())
      ;
  }

  /**
   * 注入配置
   *
   * @return
   */
  public InjectionConfig injectionConfig() {

    // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值

    return new CodeInjectionConfig()
      .setFileOutConfigList(Collections.singletonList(
        new FileOutConfig("/" + (generatorProperties.getDriverName().contains("oracle") ? "templateOracle" : "template") + "/myMapper.xml.vm") {
          // TODO : xml重定向
          @Override
          public String outputFile(TableInfo tableInfo) {
            String filePath = generatorProperties.getPath() + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";

            return filePath;
          }
        }
      ))
      ;
  }


  public static void execute(GeneratorProperties generatorProperties) {
    new CodeGenerator(generatorProperties).execute();
  }

  @Override
  public void execute() {

    this.setTemplateEngine(new NotOnlyCoverTemplateEngine())
      .setDataSource(dataSourceConfig())
      .setGlobalConfig(globalConfig())
      .setPackageInfo(packageConfig())
      .setTemplate(templateConfig())
      .setStrategy(strategyConfig())
      .setCfg(injectionConfig())
    ;

    super.execute();
  }
}

