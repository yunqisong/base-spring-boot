package com.wosummer.demo.model.dto.mission;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: SubmitMissionForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class SubmitMissionForm {

  @NotBlank(message = "任务的ID不可以为空")
  private String id;
}

