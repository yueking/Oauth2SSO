package com.yueking.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.annotation.Resource;

/**
 * 1.授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //1.实际项目一般不使用内存管理
        //配置客户、密钥、重定向地址、授权范围、授权类型:模式
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("123"))
                .redirectUris("http://www.baidu.com")
                .scopes("all")
                /**
                 * 授权类型
                 * 授权码模式 简化模式 密码模式 客户端模式
                 * authorization_code:授权码模式
                 */
                .authorizedGrantTypes("authorization_code");
    }
}
