package com.wosummer.demo.model.dto.mission;

import com.wosummer.demo.enums.PayStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: PayQuery
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class PayQuery {

  @ApiModelProperty("开始时间戳")
  @NotNull(message = "开始时间不可以该为空")
  private Long startTime;

  @ApiModelProperty("结束时间戳")
  @NotNull(message = "结束事件不可为空")
  private Long endTime;

  @ApiModelProperty("付款类型")
  private Integer paidType = PayStatusEnum.NOT_PAY.getCode();

}

