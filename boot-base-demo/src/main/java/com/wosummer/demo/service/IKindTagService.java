package com.wosummer.demo.service;

import com.wosummer.demo.model.dto.kindTag.PostKindTagForm;
import com.wosummer.demo.model.vo.kindTag.KindTagListVo;
import com.wosummer.demo.model.vo.kindTag.KindTagSingleVo;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: IKindTagService
 */
@SuppressWarnings("ALL")
public interface IKindTagService {

  /**
   * 获取所有的Tag
   *
   * @return
   */
  KindTagListVo getAllKindTag();

  /**
   * 保存标签
   *
   * @param postKindTagForm
   * @return
   */
  KindTagSingleVo postKindTag(PostKindTagForm postKindTagForm);
}
