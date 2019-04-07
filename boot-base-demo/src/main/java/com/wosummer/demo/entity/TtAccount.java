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
@TableName("`t_tt_account`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {TtAccount.class})
public class TtAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String info;
    private String account;
    private String password;
    private String type;
    private String lastlogin;
    private String cookies;


    public static final String ID = "`id`";

    public static final String NAME = "`name`";

    public static final String INFO = "`info`";

    public static final String ACCOUNT = "`account`";

    public static final String PASSWORD = "`password`";

    public static final String TYPE = "`type`";

    public static final String LASTLOGIN = "`lastlogin`";

    public static final String COOKIES = "`cookies`";

}
