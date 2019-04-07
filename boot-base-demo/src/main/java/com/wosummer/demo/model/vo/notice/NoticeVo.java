package com.wosummer.demo.model.vo.notice;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.wosummer.demo.entity.Notice;
import com.wosummer.demo.serializer.DeepStringToJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: NoticeVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NoticeVo extends Notice {

  @JSONField(serializeUsing = DeepStringToJsonSerializer.class)
  private String editor;

  public NoticeVo(Notice notice) {
    if (Objects.nonNull(notice)) {
      BeanUtil.copyProperties(notice, this);
    }
  }
}

