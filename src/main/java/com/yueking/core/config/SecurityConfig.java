package com.yueking.core.config;

import com.yueking.core.handler.MyAccessDeniedHandler;
import com.yueking.core.handler.MyAuthenticationFailureHandler;
import com.yueking.core.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Resource
    private MyAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单提交
        http.formLogin()
                //自定义登录页面
                // .loginPage("/login.html")
                .loginPage("/toLogin")
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

        //退出登录
        http.logout().logoutSuccessUrl("/login.html");

        //授权
        http.authorizeRequests()
                //配置不需要认证 放行资源 /login.html
                // .antMatchers("/login.html").permitAll()
                .antMatchers("/toLogin").permitAll()
                .antMatchers("/error.html").permitAll()
                // .antMatchers("/css/**","/js/**","/images/**").permitAll()
                .antMatchers("/images/*.png").hasRole("Admin")
                // .regexMatchers(HttpMethod.POST,"/version").permitAll()
                // .regexMatchers(HttpMethod.GET,"/version").permitAll()
                // .regexMatchers(HttpMethod.GET,"/version").hasAuthority("authorityVersion")
                // 基于权限访问
                // .antMatchers("/images/main.jpg").hasAuthority("authorityVersion")
                // 基于角色访问
                // .antMatchers("/images/main.jpg").hasAuthority("roleName")
                // 基于IP地址访问
                // .antMatchers("/images/main.jpg").hasIpAddress("127.0.0.0.1")
                //所有的请求都需要授权认证
                .anyRequest().authenticated();

        // 异常处理
        //todo 应该做成 通用异常处理并返回 json 到前端
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        //允许跨域访问
        http.authorizeRequests().and().csrf().disable();
    }
}
