package com.wosummer.demo.model.vo.template;

import com.wosummer.demo.entity.MTemplate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: TemplateSingleVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TemplateSingleVo {

  private Integer id;

  private Integer enable;

  private TemplateVo misssion;


  public TemplateSingleVo(MTemplate mTemplate) {

    this.setId(mTemplate.getId())
      .setEnable(mTemplate.getEnable())
      .setMisssion(new TemplateVo(mTemplate));
  }
}

