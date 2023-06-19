package com.github.doublewaters.saclient.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaowenqi
 * @version 1.0.0
 * @date 2023年05月31日 11:07:49
 * @packageName com.github.doublewaters.saclient.Controller
 * @className testController
 * @describe TODO
 */
@RestController
public class testController {

    @RequestMapping("/test")
    public SaResult test(){
        return  SaResult.data(StpUtil.getLoginId());
    }

}
