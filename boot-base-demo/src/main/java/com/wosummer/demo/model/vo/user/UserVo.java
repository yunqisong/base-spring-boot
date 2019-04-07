package com.wosummer.demo.model.vo.user;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.wosummer.demo.entity.User;
import com.wosummer.demo.serializer.DeepStringToJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: UserVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

  @JSONField(serializeUsing = DeepStringToJsonSerializer.class)
  @ApiModelProperty("该字段序列化成为JSON")
  private String info;

  @JSONField(serialize = false)
  @ApiModelProperty("该字段不展示")
  private String password;

  public UserVo(User user) {
    if (Objects.nonNull(user)) {
      BeanUtil.copyProperties(user, this);
    }
  }

}

