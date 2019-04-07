package com.wosummer.demo.model.vo.kindTag;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.wosummer.demo.entity.KindTag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: KindTagVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class KindTagVo extends KindTag {

  @JSONField(name = "create_time")
  private Long createTime;

  public KindTagVo(KindTag kindTag) {
    if (Objects.nonNull(kindTag)) {
      BeanUtil.copyProperties(kindTag, this);
    }
  }

}

