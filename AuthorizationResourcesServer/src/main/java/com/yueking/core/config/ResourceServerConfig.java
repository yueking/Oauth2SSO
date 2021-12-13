package com.yueking.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${resource.app.id}")
    private String appId;

    @Value("${resource.app.secret}")
    private String appSecret;

    @Value("${resource.id}")
    private String resourceId;

    @Value("${authorization.server.check_token_url}")
    private String check_token_url;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Primary
    @Bean
    public RemoteTokenServices remoteTokenServices() {
        final  RemoteTokenServices tokenServices = new RemoteTokenServices();
        // 设置授权服务check_token 端点地址
        tokenServices.setCheckTokenEndpointUrl(check_token_url);
        // 设置客户端id与secret 注意:client_secret不能使用 PasswordEncoder 加密!
        tokenServices.setClientId(appId);
        tokenServices.setClientSecret(appSecret);
        return tokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置创建session策略
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        //所有请求必须授权才能访问
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId).stateless(true);
    }
}
