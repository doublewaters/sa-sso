package com.github.doublewaters.saclient.Controller;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.sso.SaSsoProcessor;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.dtflys.forest.Forest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaowenqi
 * @version 1.0.0
 * @date 2023年05月31日 14:46:55
 * @packageName com.github.doublewaters.saclient.Controller
 * @className SsoClientController
 * @describe TODO
 */
@RestController
public class SsoClientController {
    // SSO-Client端：首页
    @RequestMapping("/")
    public String index() {
        String str = "<h2>Sa-Token SSO-Client 应用端</h2>" +
                "<p>当前会话是否登录：" + StpUtil.isLogin() + "</p>" +
                "<p><a href=\"javascript:location.href='/sso/login?back=' + encodeURIComponent(location.href);\">登录</a>" +
                " <a href='/sso/logout?back=self'>注销</a></p>";
        return str;
    }

    /*
     * SSO-Client端：处理所有SSO相关请求
     * 		http://{host}:{port}/sso/login			-- Client端登录地址，接受参数：back=登录后的跳转地址
     * 		http://{host}:{port}/sso/logout			-- Client端单点注销地址（isSlo=true时打开），接受参数：back=注销后的跳转地址
     * 		http://{host}:{port}/sso/logoutCall		-- Client端单点注销回调地址（isSlo=true时打开），此接口为框架回调，开发者无需关心
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoProcessor.instance.clientDister();
    }

    // 配置SSO相关参数
    @Autowired
    private void configSso(SaSsoConfig sso) {
        // 配置Http请求处理器
        sso.setSendHttp(url -> {
            System.out.println("------ 发起请求：" + url);
            return Forest.get(url).executeAsString();
        });
    }

    // 查询我的账号信息
//    @RequestMapping("/sso/myInfo")
//    public Object myInfo() {
//        // 组织请求参数
//        Map<String, Object> map = new HashMap<>();
//        map.put("apiType", "userinfo");
//        map.put("loginId", StpUtil.getLoginId());
//
//        // 发起请求
//        Object resData = SaSsoUtil.getData(map);
//        System.out.println("sso-server 返回的信息：" + resData);
//        return resData;
//    }

    // 全局异常拦截
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }

}
