package com.wosummer.demo.model.vo.article;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.wosummer.demo.entity.Article;
import com.wosummer.demo.serializer.DeepStringToJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: ArticleVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ArticleVo extends Article {


  @JSONField(serializeUsing = DeepStringToJsonSerializer.class)
  @ApiModelProperty("该字段输出为JSON")
  private String info;

  public ArticleVo(Article article) {
    if (Objects.nonNull(article)) {
      BeanUtil.copyProperties(article, this);
    }
  }
}

