package com.github.doublewaters.ssoserver.controller;

import cn.dev33.satoken.sso.SaSsoConsts;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0.0
 * @author zhaowenqi
 * @date 2023年05月30日 23:41:06
 * @packageName com.github.doublewaters.ssoserver
 * @className H5Controller
 * @describe TODO
 */
public class H5Controller {
    /**
     * 获取 redirectUrl
     */
    @RequestMapping("/sso/getRedirectUrl")
    private Object getRedirectUrl(String redirect, String mode, String client) {
        // 未登录情况下，返回 code=401
        if(StpUtil.isLogin() == false) {
            return SaResult.code(401);
        }
        // 已登录情况下，构建 redirectUrl
        if(SaSsoConsts.MODE_SIMPLE.equals(mode)) {
            // 模式一
            SaSsoUtil.checkRedirectUrl(SaFoxUtil.decoderUrl(redirect));
            return SaResult.data(redirect);
        } else {
            // 模式二或模式三
            String redirectUrl = SaSsoUtil.buildRedirectUrl(StpUtil.getLoginId(), client, redirect);
            return SaResult.data(redirectUrl);
        }
    }


}
