package com.wosummer.base.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @author yunqisong
 * @date 2018/10/3
 * FOR: CodeInfo 输出
 */

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeInfo {

  private String code;

  private String info;

}

