package com.wosummer.demo.model.dto.article;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: BasicArticleForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("基础文章保存表单")
public class BasicArticleForm {

  @NotBlank(message = "文章内容不可以为空")
  private String content;

  @NotBlank(message = "文章ID不可以为空")
  private String id;

  @NotBlank(message = "文章标题不可以为空")
  private String title;

  private ArticleInfo info;

}

