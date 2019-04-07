package com.wosummer.demo.model.dto.article;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: PostArticleForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("文章保存表单")
public class PostArticleForm extends BasicArticleForm {

}

