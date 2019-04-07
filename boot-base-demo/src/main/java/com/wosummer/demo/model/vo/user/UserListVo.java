package com.wosummer.demo.model.vo.user;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: UserListVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class UserListVo {

  private List<UserVo> users;

  private UserVo user;
}

