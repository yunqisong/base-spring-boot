package com.wosummer.demo.model.vo.template;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.wosummer.demo.entity.MTemplate;
import com.wosummer.demo.serializer.DeepStringToJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: TemplateVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class TemplateVo extends MTemplate {


  @JSONField(serializeUsing = DeepStringToJsonSerializer.class)
  @ApiModelProperty("该字段会被转化为JSON不再是字符串的表现形式")
  private String mission;

  public TemplateVo(MTemplate mTemplate) {
    if (Objects.nonNull(mTemplate)) {
      BeanUtil.copyProperties(mTemplate, this);
    }
  }
}

