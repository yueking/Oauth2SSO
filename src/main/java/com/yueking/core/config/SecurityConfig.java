package com.yueking.core.config;

import com.yueking.core.handler.MyAuthenticationFailureHandler;
import com.yueking.core.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                //自定义登录参数
                .usernameParameter("username")
                .passwordParameter("password")
                //对应表单提交地址
                .loginProcessingUrl("/submitLogin")
                //定义登录成功或失败重定向地址
                // .successForwardUrl("/toMain")
                // .failureForwardUrl("/toError")
                //自定义登录成功或失败都处理器
                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));

        //表单提交

        //授权
        http.authorizeRequests()
                //不需要认证 放行/login.html
                .antMatchers("/login.html").permitAll()
                .antMatchers("/error.html").permitAll()
                //所有的请求都需要授权认证
                .anyRequest().authenticated();
        //允许跨域访问
        http.authorizeRequests().and().csrf().disable();
    }
}
