package com.wosummer.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
@TableName("`t_kind_tag`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {KindTag.class})
public class KindTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.INPUT)
    private String id;
    private String kind;
    private String tag;
    private Long createTime;
    private Integer enable;
    private String info;


    public static final String ID = "`id`";

    public static final String KIND = "`kind`";

    public static final String TAG = "`tag`";

    public static final String CREATE_TIME = "`create_time`";

    public static final String ENABLE = "`enable`";

    public static final String INFO = "`info`";

}
