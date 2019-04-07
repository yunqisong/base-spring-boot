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
@TableName("`t_article`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {Article.class})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.INPUT)
    private String id;
    private String title;
    private String content;
    private String author;
    private String state;
    private String info;


    public static final String ID = "`id`";

    public static final String TITLE = "`title`";

    public static final String CONTENT = "`content`";

    public static final String AUTHOR = "`author`";

    public static final String STATE = "`state`";

    public static final String INFO = "`info`";

}
