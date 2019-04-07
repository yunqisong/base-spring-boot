package com.wosummer.demo.model.vo.mission;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.wosummer.demo.entity.Mission;
import com.wosummer.demo.serializer.DeepStringToJsonSerializer;
import com.wosummer.demo.serializer.DotStringToArraySerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: MissionVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MissionVo extends Mission {

  @JSONField(name = "example_images", serializeUsing = DotStringToArraySerializer.class)
  @ApiModelProperty(name = "example_images", value = "该字段输出为 example_images 数组")
  private String exampleImages;

  @JSONField(serializeUsing = DeepStringToJsonSerializer.class)
  @ApiModelProperty("该字段输出为JSON")
  private String tags;

  @JSONField(serializeUsing = DeepStringToJsonSerializer.class)
  @ApiModelProperty("该字段输出为JSON")
  private String info;

  public MissionVo(Mission mission) {
    if (Objects.nonNull(mission)) {
      BeanUtil.copyProperties(mission, this);
    }
  }
}

