package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import com.wosummer.demo.model.dto.article.PostArticleForm;
import com.wosummer.demo.model.dto.article.SuggestionForm;
import com.wosummer.demo.model.vo.article.ArticleVo;
import com.wosummer.demo.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.wosummer.demo.enums.Const.HAS_ANY_ROLE_ADMIN;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: ArticleController
 */
@Slf4j
@SuppressWarnings("ALL")
@RestController
@RequestMapping("article")
@Api(tags = "文章相关 API")
public class ArticleController extends BaseController {

  @Autowired
  private IArticleService articleService;

  @GetMapping
  @ApiOperation("获取文章")
  public Result<ArticleVo> getArticle(@Valid @NotBlank(message = "查找的文章ID不可以为空") @RequestParam String id) {

    return success(articleService.getArticle(id));
  }

  @PostMapping
  @ApiOperation("保存文章")
  public Result<ArticleVo> postArticle(@Valid @RequestBody PostArticleForm postArticleForm) {

    return success(articleService.postArticle(postArticleForm));
  }

  @PostMapping("suggestion")
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  @ApiOperation("提交文章意见")
  public Result<Boolean> suggestion(@Valid @RequestBody SuggestionForm suggestionForm) {

    return success(articleService.suggestion(suggestionForm));
  }

}


