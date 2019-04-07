package com.wosummer.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author yunqisong
 * @since 2019-04-05
 */
@Data
@SuppressWarnings("ALL")
@Accessors(chain = true)
@TableName("`t_repeat_rate_check`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {RepeatRateCheck.class})
public class RepeatRateCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    private Integer id;
    private String username;
    private Integer userType;
    private Integer userId;
    private String missionId;
    private String state;
    private Long createTime;
    private Long deadline;
    private Integer checkValue;
    private String checkImageUrl;
    private String enable;
    private String info;


    public static final String ID = "`id`";

    public static final String USERNAME = "`username`";

    public static final String USER_TYPE = "`user_type`";

    public static final String USER_ID = "`user_id`";

    public static final String MISSION_ID = "`mission_id`";

    public static final String STATE = "`state`";

    public static final String CREATE_TIME = "`create_time`";

    public static final String DEADLINE = "`deadline`";

    public static final String CHECK_VALUE = "`check_value`";

    public static final String CHECK_IMAGE_URL = "`check_image_url`";

    public static final String ENABLE = "`enable`";

    public static final String INFO = "`info`";

}
