package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import com.wosummer.demo.model.dto.notice.NoticeForm;
import com.wosummer.demo.model.vo.notice.NoticeVo;
import com.wosummer.demo.service.INoticeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.wosummer.demo.enums.Const.HAS_ANY_ROLE_ADMIN;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: NoticeController
 */
@Slf4j
@SuppressWarnings("ALL")
@RequestMapping("notice")
@RestController
@Api(tags = "公告相关 API")
public class NoticeController extends BaseController {

  @Autowired
  private INoticeService noticeService;

  @PostMapping
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  public Result<NoticeVo> updateNotice(@Valid @RequestBody NoticeForm noticeForm) {

    return success(noticeService.updateNotice(noticeForm));
  }

  @GetMapping
  public Result<NoticeVo> getNotice() {

    return success(noticeService.getNotice());
  }

}

