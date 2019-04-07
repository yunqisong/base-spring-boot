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
@TableName("`t_record`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {Record.class})
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    private Integer id;
    private String info;
    private String method;
    private String apiResult;
    private String typeInfo;
    private String nickname;
    private String api;
    private String type;
    private Integer uid;
    private String aid;
    private String mid;
    private Long time;


    public static final String ID = "`id`";

    public static final String INFO = "`info`";

    public static final String METHOD = "`method`";

    public static final String API_RESULT = "`api_result`";

    public static final String TYPE_INFO = "`type_info`";

    public static final String NICKNAME = "`nickname`";

    public static final String API = "`api`";

    public static final String TYPE = "`type`";

    public static final String UID = "`uid`";

    public static final String AID = "`aid`";

    public static final String MID = "`mid`";

    public static final String TIME = "`time`";

}
