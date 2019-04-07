package com.wosummer.demo.model.vo.kindTag;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: KindTagListVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class KindTagListVo {

  private List<KindTagResultVo> list;
}

