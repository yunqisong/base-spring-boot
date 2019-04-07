package com.wosummer.demo.model.dto.mission;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: PostPaidForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class PostPaidForm {

  @NotEmpty(message = "付款任务不能为空")
  private List<String> missions;
}

