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
@TableName("`t_user`")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "", subTypes = {User.class})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer role;
    private String info;


    public static final String ID = "`id`";

    public static final String USERNAME = "`username`";

    public static final String PASSWORD = "`password`";

    public static final String ROLE = "`role`";

    public static final String INFO = "`info`";

}
