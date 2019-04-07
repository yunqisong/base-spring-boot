package com.wosummer.demo.model.dto.article;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: SuggestionForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class SuggestionForm {

  @NotBlank(message = "文章ID不能为空")
  private String id;

  @NotBlank(message = "建议不能为空")
  private String suggestion;

}

