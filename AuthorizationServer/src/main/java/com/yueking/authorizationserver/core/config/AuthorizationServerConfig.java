package com.yueking.authorizationserver.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;

/**
 * 认证服务器 server
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单提交
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("admin")
                .secret(passwordEncoder.encode("admin"))
                .scopes("all")
                //令牌过期时间60秒
                .accessTokenValiditySeconds(60 * 60)
                //刷新令牌过期时间1小时
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30)
                //自动授权
                .autoApprove(true)
                .resourceIds("yueking_resource")
                .redirectUris("http://localhost:8080/callback")
                /**
                 * 授权码模式 简化模式 密码模式 客户端模式
                 * authorization_code refresh_token password client_credentials implicit
                 */
                .authorizedGrantTypes("authorization_code","refresh_token");
    }
}
