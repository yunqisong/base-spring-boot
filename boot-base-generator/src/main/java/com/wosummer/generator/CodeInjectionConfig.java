package com.wosummer.generator;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author yunqisong
 * @date 2018/9/28
 * FOR: TODO:
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CodeInjectionConfig extends InjectionConfig {

    @Override
    public void initMap() {

        this.setMap(Dict.create().set("keep", Boolean.TRUE));
    }

}

