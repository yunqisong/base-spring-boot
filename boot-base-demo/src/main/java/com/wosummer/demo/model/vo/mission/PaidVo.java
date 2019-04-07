package com.wosummer.demo.model.vo.mission;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-07
 * @for: PaidVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class PaidVo {

  private List<String> fail;

  private List<String> success;

  public PaidVo() {
    this.fail = new ArrayList<>();
    this.success = new ArrayList<>();
  }
}

