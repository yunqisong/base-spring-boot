package com.wosummer.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wosummer.base.kit.SecurityKit;
import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import com.wosummer.base.service.CommonService;
import com.wosummer.demo.entity.Notice;
import com.wosummer.demo.enums.EnableEnum;
import com.wosummer.demo.mapper.NoticeMapper;
import com.wosummer.demo.model.dto.notice.NoticeForm;
import com.wosummer.demo.model.jwt.JwtUser;
import com.wosummer.demo.model.vo.notice.NoticeVo;
import com.wosummer.demo.service.INoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.wosummer.demo.enums.Const.LIMIT_1;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: NoticeServiceImpl
 */
@Slf4j
@SuppressWarnings("ALL")
@Service
public class NoticeServiceImpl implements INoticeService {

  @Autowired
  private NoticeMapper noticeMapper;

  @Autowired
  private CommonService commonService;

  /**
   * 获取当前公告
   *
   * @return
   */
  @Override
  public NoticeVo getNotice() {

    return Optional
      .ofNullable(getNewNotice())
      .map(NoticeVo::new)
      .orElseThrow(() -> new LogicException(BaseResultEnum.NOTHING_FOUND));
  }

  /**
   * 获取当前最新公告
   *
   * @return
   */
  private Notice getNewNotice() {
    return noticeMapper.selectOne(
      new QueryWrapper<Notice>()
        .eq(Notice.ENABLE, EnableEnum.YES.getCode())
        .orderByDesc(Notice.ID)
        .last(LIMIT_1)
    );
  }

  /**
   * 更新公告
   *
   * @param noticeForm
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public NoticeVo updateNotice(NoticeForm noticeForm) {
    // 禁用老的
    Optional.ofNullable(getNewNotice()).ifPresent(oldNotice -> commonService.insertOrUpdate(oldNotice.setEnable(EnableEnum.NO.getCode()), noticeMapper));

    Notice notice = new Notice()
      .setContent(noticeForm.getContent())
      .setEnable(EnableEnum.YES.getCode())
      .setCreated(DateTime.now().getTime())
      .setTitle(noticeForm.getTitle())
      .setEditor(JSON.toJSONString(((JwtUser) SecurityKit.currentUser()).getUser()));

    commonService.insertOrUpdate(notice, noticeMapper);

    return getNotice();
  }

}

