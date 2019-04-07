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
@TableName("`t_m_template`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {MTemplate.class})
public class MTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    private Integer id;
    private String mission;
    private Integer enable;


    public static final String ID = "`id`";

    public static final String MISSION = "`mission`";

    public static final String ENABLE = "`enable`";

}
