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
@TableName("`t_notice`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {Notice.class})
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Long created;
    private Integer enable;
    private String editor;


    public static final String ID = "`id`";

    public static final String TITLE = "`title`";

    public static final String CONTENT = "`content`";

    public static final String CREATED = "`created`";

    public static final String ENABLE = "`enable`";

    public static final String EDITOR = "`editor`";

}
