package com.wosummer.demo.service.impl;

import cn.hutool.json.JSONUtil;
import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import com.wosummer.base.service.CommonService;
import com.wosummer.demo.entity.Article;
import com.wosummer.demo.mapper.ArticleMapper;
import com.wosummer.demo.model.dto.article.PostArticleForm;
import com.wosummer.demo.model.dto.article.SuggestionForm;
import com.wosummer.demo.model.vo.article.ArticleVo;
import com.wosummer.demo.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: ArticleServiceImpl
 */
@Slf4j
@SuppressWarnings("ALL")
@Service
public class ArticleServiceImpl implements IArticleService {

  @Autowired
  private ArticleMapper articleMapper;

  @Autowired
  private CommonService commonService;

  @Override
  public ArticleVo getArticle(String id) {


    return Optional.ofNullable(articleMapper.selectById(id)).map(ArticleVo::new).orElseThrow(() -> new LogicException(BaseResultEnum.NOTHING_FOUND));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public ArticleVo postArticle(PostArticleForm postArticleForm) {

    Article article = articleMapper.selectById(postArticleForm.getId());

    if (Objects.isNull(article)) {
      throw new LogicException(BaseResultEnum.NOTHING_FOUND);
    }

    article
      .setTitle(postArticleForm.getTitle())
      .setContent(postArticleForm.getContent());

    commonService.insertOrUpdate(article, articleMapper);

    return new ArticleVo(article);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Boolean suggestion(SuggestionForm suggestionForm) {

    Article article = articleMapper.selectById(suggestionForm.getId());

    if (Objects.isNull(article)) {
      throw new LogicException(BaseResultEnum.NOTHING_FOUND);
    }

    article.setInfo(JSONUtil.parseObj(suggestionForm).toString());

    return commonService.insertOrUpdate(article, articleMapper);
  }


}

