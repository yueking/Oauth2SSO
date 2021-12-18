package com.yueking.core.config;

import com.yueking.core.dao.entity.SysPermission;
import com.yueking.core.dao.repository.PermissionDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PermissionDao permissionDao;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //登录认证
        //配置 userDetailsService:加载用户 及配置 passwordEncoder:密码的加密与解码方式进行用户验证
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //从数据库中动态加载 权限Tag 权限Url 配置权限过滤规则
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();

        List<SysPermission> permissionList = permissionDao.findAll();
        for (SysPermission permission : permissionList) {
            authorizeRequests.antMatchers(permission.getPermName()).hasAuthority(permission.getPermTag());
        }
        authorizeRequests.antMatchers("/failed/**").permitAll();
        authorizeRequests.antMatchers("/callback/**").permitAll()
                .antMatchers("/**").fullyAuthenticated().and().httpBasic().and().csrf().disable();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
