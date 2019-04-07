package com.wosummer.demo.model.dto.mission;

import com.wosummer.base.model.query.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: MissionQuery
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("任务分页")
public class MissionQuery extends PageQuery {

  @ApiModelProperty("发送人ID")
  private Integer receiverId;

  @ApiModelProperty("接收人ID")
  private Integer postId;

  @ApiModelProperty("逗号分隔的状态")
  private String state;

  @ApiModelProperty("Tag? JSON string")
  private String tag;

}

