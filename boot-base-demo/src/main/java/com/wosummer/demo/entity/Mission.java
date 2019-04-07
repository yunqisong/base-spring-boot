package com.wosummer.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("`t_mission`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {Mission.class})
public class Mission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.INPUT)
    private String id;
    private String content;
    private String type;
    private String info;
    private String state;
    private Long deadline;
    private Long created;
    private Integer paid;
    private Integer enable;
    @TableField("`articleId`")
    private String articleId;
    @TableField("`articleNum`")
    private Integer articleNum;
    @TableField("`articleTitle`")
    private String articleTitle;
    private Integer postid;
    private Integer price;
    @TableField("`receiveTime`")
    private Long receiveTime;
    @TableField("`receiverAccount`")
    private String receiverAccount;
    @TableField("`receiverId`")
    private Integer receiverId;
    @TableField("`receiverName`")
    private String receiverName;
    private Integer rules;
    private Long submittime;
    private Long passtime;
    private String tags;
    private String exampleImages;


    public static final String ID = "`id`";

    public static final String CONTENT = "`content`";

    public static final String TYPE = "`type`";

    public static final String INFO = "`info`";

    public static final String STATE = "`state`";

    public static final String DEADLINE = "`deadline`";

    public static final String CREATED = "`created`";

    public static final String PAID = "`paid`";

    public static final String ENABLE = "`enable`";

    public static final String ARTICLEID = "`articleId`";

    public static final String ARTICLENUM = "`articleNum`";

    public static final String ARTICLETITLE = "`articleTitle`";

    public static final String POSTID = "`postid`";

    public static final String PRICE = "`price`";

    public static final String RECEIVETIME = "`receiveTime`";

    public static final String RECEIVERACCOUNT = "`receiverAccount`";

    public static final String RECEIVERID = "`receiverId`";

    public static final String RECEIVERNAME = "`receiverName`";

    public static final String RULES = "`rules`";

    public static final String SUBMITTIME = "`submittime`";

    public static final String PASSTIME = "`passtime`";

    public static final String TAGS = "`tags`";

    public static final String EXAMPLE_IMAGES = "`example_images`";

}
