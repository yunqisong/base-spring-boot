package com.wosummer.demo.model.dto.mission;

import com.wosummer.demo.model.vo.kindTag.KindTagVo;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: TemplateTagInfo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class MissionInfo {

  private List<KindTagVo> tags;

  private Integer price;

  private Integer articleNum;

}

