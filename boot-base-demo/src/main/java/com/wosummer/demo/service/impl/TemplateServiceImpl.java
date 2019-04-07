package com.wosummer.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wosummer.base.service.CommonService;
import com.wosummer.demo.entity.MTemplate;
import com.wosummer.demo.entity.Mission;
import com.wosummer.demo.enums.EnableEnum;
import com.wosummer.demo.mapper.MTemplateMapper;
import com.wosummer.demo.model.dto.template.PostTemplateForm;
import com.wosummer.demo.model.vo.template.TemplateSingleVo;
import com.wosummer.demo.model.vo.template.TemplateVo;
import com.wosummer.demo.service.ITemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: TemplateServiceImpl
 */
@Slf4j
@SuppressWarnings("ALL")
@Service
public class TemplateServiceImpl implements ITemplateService {

  @Autowired
  private CommonService commonService;

  @Autowired
  private MTemplateMapper mTemplateMapper;

  /**
   * 创建、保存模板
   *
   * @param postTemplateForm
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public TemplateSingleVo createTemplate(PostTemplateForm postTemplateForm) {

    MTemplate mTemplate = Optional.ofNullable(postTemplateForm.getId()).map(mTemplateMapper::selectById).orElseGet(MTemplate::new);

    mTemplate.setEnable(EnableEnum.YES.getCode()).setMission(JSON.toJSONString(postTemplateForm.setId(mTemplate.getId())));

    commonService.insertOrUpdate(mTemplate, mTemplateMapper);

    return new TemplateSingleVo(mTemplate);
  }

  /**
   * 获取所有模板
   *
   * @return
   */
  @Override
  public List<TemplateSingleVo> getTemplate() {

    return mTemplateMapper
      .selectList(
        new QueryWrapper<MTemplate>()
          .eq(MTemplate.ENABLE, EnableEnum.YES.getCode())
      )
      .stream()
      .map(TemplateSingleVo::new)
      .collect(Collectors.toList());
  }

  /**
   * 删除模板
   *
   * @param templateId
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean deleteTemplate(Integer templateId) {

    return mTemplateMapper.updateById(new MTemplate().setEnable(EnableEnum.NO.getCode()).setId(templateId)) > 0;
  }
}

