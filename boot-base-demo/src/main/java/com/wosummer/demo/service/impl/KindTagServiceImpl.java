package com.wosummer.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.wosummer.base.service.CommonService;
import com.wosummer.demo.entity.KindTag;
import com.wosummer.demo.enums.EnableEnum;
import com.wosummer.demo.mapper.KindTagMapper;
import com.wosummer.demo.model.dto.kindTag.PostKindTagForm;
import com.wosummer.demo.model.vo.kindTag.KindTagListVo;
import com.wosummer.demo.model.vo.kindTag.KindTagResultVo;
import com.wosummer.demo.model.vo.kindTag.KindTagSingleVo;
import com.wosummer.demo.model.vo.kindTag.KindTagVo;
import com.wosummer.demo.service.IKindTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.wosummer.demo.enums.Const.LIMIT_1;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: KindTagServiceImpl
 */
@Slf4j
@SuppressWarnings("ALL")
@Service
public class KindTagServiceImpl implements IKindTagService {

  private static final String[] SORT_ARR = {"zhonglei", "leixing", "zishu", "tupian", "chachong"};

  @Autowired
  private KindTagMapper kindTagMapper;

  @Autowired
  private CommonService commonService;

  /**
   * 获取所有分组标签
   *
   * @return
   */
  @Override
  public KindTagListVo getAllKindTag() {
    // 查询
    List<KindTag> kindTagList = kindTagMapper.selectList(new QueryWrapper<KindTag>().eq(KindTag.ENABLE, EnableEnum.YES.getCode()));

    // 分组
    Map<String, List<KindTagVo>> kindTagMap = kindTagList.stream().map(KindTagVo::new).collect(Collectors.groupingBy(KindTagVo::getKind));

    // 组装
    List<KindTagResultVo> kindTagVos = Lists.newArrayList();
    kindTagMap.forEach((k, v) -> kindTagVos.add(new KindTagResultVo().setKind(k).setTag(v)));

    // 排序
    kindTagVos.sort((k1, k2) -> ArrayUtil.indexOf(SORT_ARR, k1.getKind()) - ArrayUtil.indexOf(SORT_ARR, k2.getKind()));

    return new KindTagListVo().setList(kindTagVos);
  }

  /**
   * 1. 数据表中没有对应种类的标签 新建标签
   * 2. 数据表中有对应种类的标签 且是未删除状态 返回已有记录
   * 3. 数据表中有对应种类的标签 且是删除状态 置为未删除状态
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public KindTagSingleVo postKindTag(PostKindTagForm postKindTagForm) {
    if (Objects.nonNull(postKindTagForm.getId())) {
      return handChangeEnable(postKindTagForm);
    }
    return handSaveKindTag(postKindTagForm);
  }

  /**
   * 保存/新建标签逻辑
   *
   * @param postKindTagForm
   * @return
   */
  public KindTagSingleVo handSaveKindTag(PostKindTagForm postKindTagForm) {
    KindTag kindTag = kindTagMapper.selectOne(
      new QueryWrapper<KindTag>()
        .eq(KindTag.KIND, postKindTagForm.getKind())
        .eq(KindTag.TAG, postKindTagForm.getKind())
        .last(LIMIT_1)
    );

    if (kindTag == null) {
      // 查询当前kind的第几个根据对应生成ID
      int count = kindTagMapper.selectCount(new QueryWrapper<KindTag>().eq(KindTag.KIND, postKindTagForm.getKind()));
      kindTag = new KindTag()
        .setCreateTime(DateTime.now().getTime())
        .setId(String.format("%s_%04d", postKindTagForm.getKind(), count + 1));
    }

    kindTag
      .setEnable(EnableEnum.YES.getCode())
      .setKind(postKindTagForm.getKind())
      .setTag(postKindTagForm.getTag());

    commonService.insertOrUpdate(kindTag, kindTagMapper);

    return new KindTagSingleVo()
      .setResult(
        new KindTagSingleVo.KindTagResult()
          .setKindTag(new KindTagVo(kindTag))
          .setMsg("标签已创建")
      );
  }

  /**
   * 启用、禁用标签逻辑
   *
   * @param postKindTagForm
   * @return
   */
  @Transactional(rollbackFor = Exception.class)
  public KindTagSingleVo handChangeEnable(PostKindTagForm postKindTagForm) {
    KindTag kindTag = kindTagMapper.selectById(postKindTagForm.getId());

    if (EnableEnum.isEnable(kindTag.getEnable())) {
      kindTag.setEnable(EnableEnum.NO.getCode());
    } else {
      kindTag.setEnable(EnableEnum.YES.getCode());
    }

    commonService.insertOrUpdate(kindTag, kindTagMapper);

    return null;
  }
}

