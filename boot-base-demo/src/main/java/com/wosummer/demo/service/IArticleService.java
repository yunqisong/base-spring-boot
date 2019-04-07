package com.wosummer.demo.service;

import com.wosummer.demo.model.dto.article.PostArticleForm;
import com.wosummer.demo.model.dto.article.SuggestionForm;
import com.wosummer.demo.model.vo.article.ArticleVo;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: IArticleService
 */
@SuppressWarnings("ALL")
public interface IArticleService {

  /**
   * 获取一篇文章
   *
   * @param id
   * @return
   */
  ArticleVo getArticle(String id);

  /**
   * 保存一片文章
   *
   * @param postArticleForm
   * @return
   */
  ArticleVo postArticle(PostArticleForm postArticleForm);

  /**
   * 文章建议
   *
   * @param suggestionForm
   * @return
   */
  Boolean suggestion(SuggestionForm suggestionForm);
}
