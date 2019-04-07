package com.wosummer.demo.generator;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: IBaseIdGenerator
 */
@SuppressWarnings("ALL")
public interface IBaseIdGenerator {

  /**
   * 生成几个ID
   *
   * @param count
   * @return
   */
  String[] getIds(Integer count);
}
