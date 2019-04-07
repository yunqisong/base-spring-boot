package com.wosummer.demo.model.vo.mission;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: MissionListVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class MissionListVo {

  private List<MissionVo> list;

  private List<String> tag;

  private Long total;
}

