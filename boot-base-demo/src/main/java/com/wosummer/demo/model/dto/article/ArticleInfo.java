package com.wosummer.demo.model.dto.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: ArticleInfo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("文章附属信息")
public class ArticleInfo {

  @ApiModelProperty("任务ID")
  @NotBlank(message = "任务ID不可以为空")
  private String mid;

  private Integer authority = 0;

}

