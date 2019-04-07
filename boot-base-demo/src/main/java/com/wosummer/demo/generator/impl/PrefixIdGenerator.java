package com.wosummer.demo.generator.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import com.wosummer.demo.generator.IBaseIdGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: AbstractPrefixIdGenerator
 */
@Slf4j
@SuppressWarnings("ALL")
public class PrefixIdGenerator implements IBaseIdGenerator {

  private String version;

  private Integer bitCount;

  private Prefix prefix;

  public PrefixIdGenerator(Integer bitCount, Prefix prefix) {
    this.bitCount = bitCount;
    this.prefix = prefix;
    this.version = "VB";
  }


  @FunctionalInterface
  public static interface Prefix {
    /**
     * 获取前缀
     *
     * @return
     */
    String getPrefix();
  }


  private String getRandomNumberWithTime() {

    return DateTime.now().getTime() + RandomUtil.randomNumbers(this.bitCount);
  }

  @Override
  public String[] getIds(Integer count) {
    String[] ids = new String[count];
    for (int i = 0; i < count; i++) {
      ids[i] = String.format("%s%s%s", this.version, this.prefix.getPrefix(), this.getRandomNumberWithTime());
    }
    return ids;
  }

}

