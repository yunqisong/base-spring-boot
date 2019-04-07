package com.wosummer.base.model.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yunqisong
 * @date 2018/10/3
 * FOR: 查询分页参数
 */

@Data
@Accessors(chain = true)
@ApiModel("分页参数")
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery implements Serializable {

  @ApiModelProperty("当前页 第0页等于第1页")
  protected Long pagesize;

  @ApiModelProperty("页大小")
  protected Long pagenum;

  public <T> Page<T> toPage() {

    return new Page<>(pagenum == null ? 1 : pagenum, pagesize == null ? 10 : pagesize);
  }


}
