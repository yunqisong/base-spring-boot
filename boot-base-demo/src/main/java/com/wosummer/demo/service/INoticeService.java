package com.wosummer.demo.service;

import com.wosummer.demo.model.dto.notice.NoticeForm;
import com.wosummer.demo.model.vo.notice.NoticeVo;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: INoticeService
 */
public interface INoticeService {


  /**
   * 获取最新的公告
   *
   * @return
   */
  NoticeVo getNotice();

  /**
   * 更新最新公告
   *
   * @param noticeForm
   * @return
   */
  NoticeVo updateNotice(NoticeForm noticeForm);
}
