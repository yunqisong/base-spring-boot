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
@TableName("`t_tag`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {Tag.class})
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`tag`", type = IdType.INPUT)
    private String tag;


    public static final String TAG = "`tag`";

}
