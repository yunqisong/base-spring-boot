package com.wosummer.demo.model.dto.notice;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: NoticeForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class NoticeForm {

  private String title;

  @NotBlank(message = "公告内容不可以为空")
  private String content;

}

