package com.wosummer.demo.model.vo.kindTag;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: KindTagSingleVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class KindTagSingleVo {

  private KindTagResult result;

  @Data
  @Accessors(chain = true)
  public static class KindTagResult {

    private String msg;

    private KindTagVo kindTag;

  }

}



