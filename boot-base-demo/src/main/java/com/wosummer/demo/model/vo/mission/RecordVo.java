package com.wosummer.demo.model.vo.mission;

import cn.hutool.core.bean.BeanUtil;
import com.wosummer.demo.entity.Record;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-07
 * @for: RecordVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RecordVo extends Record {

  public RecordVo(Record record) {
    if (Objects.nonNull(record)) {
      BeanUtil.copyProperties(record, this);
    }
  }
}

