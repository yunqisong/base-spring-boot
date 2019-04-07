package com.wosummer.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author yunqisong
 * @date 2018/9/28
 * FOR:针对 Velocity 模板引擎实现文件输出增强XML模板的改进
 */

@Slf4j
@SuppressWarnings("ALL")
public class NotOnlyCoverTemplateEngine extends VelocityTemplateEngine {


  public static final String BASE_RESULT_MAP = "BaseResultMap";
  public static final String BASE_COLUMN_LIST = "Base_Column_List";

  /**
   * <p>
   * 输出 java xml 文件
   * </p>
   */
  @Override
  public AbstractTemplateEngine batchOutput() {
    try {
      List<TableInfo> tableInfoList = getConfigBuilder().getTableInfoList();
      for (TableInfo tableInfo : tableInfoList) {
        Map<String, Object> objectMap = getObjectMap(tableInfo);
        Map<String, String> pathInfo = getConfigBuilder().getPathInfo();
        TemplateConfig template = getConfigBuilder().getTemplate();
        // 自定义内容
        InjectionConfig injectionConfig = getConfigBuilder().getInjectionConfig();
        if (null != injectionConfig) {
          injectionConfig.initMap();
          objectMap.put("cfg", injectionConfig.getMap());
          List<FileOutConfig> focList = injectionConfig.getFileOutConfigList();
          if (CollectionUtils.isNotEmpty(focList)) {
            for (FileOutConfig foc : focList) {
              String fileAttr = foc.outputFile(tableInfo);
              if (isCreate(FileType.OTHER, fileAttr)) {
                if (CollUtil.isNotEmpty(injectionConfig.getMap()) && Boolean.TRUE.equals(injectionConfig.getMap().get("keep"))) {
                  if (FileUtil.exist(fileAttr) && fileAttr.endsWith("Mapper.xml")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    Document document = load(fileAttr);
                    Element root = document.getRootElement();
                    for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                      Element el = (Element) i.next();
                      String id = el.attribute("id").getValue();
                      if (!id.equals(BASE_RESULT_MAP) && !id.equals(BASE_COLUMN_LIST)) {
                        stringBuilder.append(el.asXML()).append("\n");
                      }
                      injectionConfig.getMap().put("keep", stringBuilder.toString());
                    }
                  } else {
                    injectionConfig.getMap().put("keep", "\n");
                  }

                }
                writer(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
              }
            }
          }
        }
        // Mp.java
        String entityName = tableInfo.getEntityName();
        if (null != entityName && null != pathInfo.get(ConstVal.ENTITY_PATH)) {
          String entityFile = String.format((pathInfo.get(ConstVal.ENTITY_PATH) + File.separator + "%s" + suffixJavaOrKt()), entityName);
          if (isCreate(FileType.ENTITY, entityFile)) {
            writer(objectMap, templateFilePath(template.getEntity(getConfigBuilder().getGlobalConfig().isKotlin())), entityFile);
          }
        }
        // MpMapper.java
        if (null != tableInfo.getMapperName() && null != pathInfo.get(ConstVal.MAPPER_PATH)) {
          String mapperFile = String.format((pathInfo.get(ConstVal.MAPPER_PATH) + File.separator + tableInfo.getMapperName() + suffixJavaOrKt()), entityName);
          if (isCreate(FileType.MAPPER, mapperFile)) {
            if (!FileUtil.exist(mapperFile)) {
              // 不重复生成Mapper
              writer(objectMap, templateFilePath(template.getMapper()), mapperFile);
            }
          }
        }
        // MpMapper.xml
        if (null != tableInfo.getXmlName() && null != pathInfo.get(ConstVal.XML_PATH)) {
          String xmlFile = String.format((pathInfo.get(ConstVal.XML_PATH) + File.separator + tableInfo.getXmlName() + ConstVal.XML_SUFFIX), entityName);
          if (isCreate(FileType.XML, xmlFile)) {
            writer(objectMap, templateFilePath(template.getXml()), xmlFile);
          }
        }
        // IMpService.java
        if (null != tableInfo.getServiceName() && null != pathInfo.get(ConstVal.SERVICE_PATH)) {
          String serviceFile = String.format((pathInfo.get(ConstVal.SERVICE_PATH) + File.separator + tableInfo.getServiceName() + suffixJavaOrKt()), entityName);
          if (isCreate(FileType.SERVICE, serviceFile)) {
            writer(objectMap, templateFilePath(template.getService()), serviceFile);
          }
        }
        // MpServiceImpl.java
        if (null != tableInfo.getServiceImplName() && null != pathInfo.get(ConstVal.SERVICE_IMPL_PATH)) {
          String implFile = String.format((pathInfo.get(ConstVal.SERVICE_IMPL_PATH) + File.separator + tableInfo.getServiceImplName() + suffixJavaOrKt()), entityName);
          if (isCreate(FileType.SERVICE_IMPL, implFile)) {
            writer(objectMap, templateFilePath(template.getServiceImpl()), implFile);
          }
        }
        // MpController.java
        if (null != tableInfo.getControllerName() && null != pathInfo.get(ConstVal.CONTROLLER_PATH)) {
          String controllerFile = String.format((pathInfo.get(ConstVal.CONTROLLER_PATH) + File.separator + tableInfo.getControllerName() + suffixJavaOrKt()), entityName);
          if (isCreate(FileType.CONTROLLER, controllerFile)) {
            writer(objectMap, templateFilePath(template.getController()), controllerFile);
          }
        }
      }
    } catch (
      Exception e) {
      logger.error("无法创建文件，请检查配置信息！", e);
    }
    return this;
  }


  public static Document load(String filename) {
    Document document = null;
    try {
      SAXReader saxReader = new SAXReader();
      document = saxReader.read(new File(filename));
      // 读取XML文件,获得document对象
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return document;
  }


}

