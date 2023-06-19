package com.github.doublewaters.ssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
        System.out.println("--------------------------------\n" +
                "------ SSO 认证中心启动成功! ------\n" +
                "--------------------------------\n");
    }

}
