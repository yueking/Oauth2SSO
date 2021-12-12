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
        // auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123")).authorities("addMember","delMember","updateMember","showMember");
        // auth.inMemoryAuthentication().withUser("add").password(passwordEncoder().encode("add")).authorities("addMember");
        // auth.inMemoryAuthentication().withUser("del").password(passwordEncoder().encode("del")).authorities("delMember");
        // auth.inMemoryAuthentication().withUser("update").password(passwordEncoder().encode("update")).authorities("updateMember");
        // auth.inMemoryAuthentication().withUser("show").password(passwordEncoder().encode("show")).authorities("showMember");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置认证方式 token 表单 basic模式
        // http.authorizeRequests()
        //         .antMatchers("/addMember").hasAuthority("addMember")
        //         .antMatchers("/delMember").hasAuthority("delMember")
        //         .antMatchers("/updateMember").hasAuthority("updateMember")
        //         .antMatchers("/showMember").hasAuthority("showMember")
        //         .antMatchers("/**")
        //         .fullyAuthenticated().and()
        //         // 基础模式
        //         // .httpBasic();
        //         // 表单模式
        //         .formLogin();

        //从数据库中动态加载 权限Tag 权限Url
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();

        List<SysPermission> permissionList = permissionDao.findAll();
        for (SysPermission permission : permissionList) {
          authorizeRequests.antMatchers(permission.getPermName()).hasAuthority(permission.getPermTag());
        }
        authorizeRequests.antMatchers("/failed/**").permitAll()
                .antMatchers("/**").fullyAuthenticated().and().formLogin().and().csrf().disable();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
