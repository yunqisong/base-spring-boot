package com.wosummer.demo.model.dto.kindTag;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: PostKindTagForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class PostKindTagForm {

  private String id;

  @NotBlank(message = "种类不可以为空")
  private String kind;

  @NotBlank(message = "标签名不可以为空")
  private String tag;
}

