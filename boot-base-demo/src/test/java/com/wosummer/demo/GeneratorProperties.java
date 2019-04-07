package com.wosummer.demo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author yunqisong
 * @date 2018/9/28
 * FOR: TODO:
 */

@Data
@Accessors(chain = true)
public class GeneratorProperties {

  private String path;

  private String idType;

  private String author;

  private String basePackage;

  private String prefix;

  private String tables;

  private String url;

  private String driverName;

  private String username;

  private String password;

}

