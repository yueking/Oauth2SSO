package com.yueking.core.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名去数据库中查询用户信息
        if(!username.equals("admin")){
            throw new UsernameNotFoundException("用户不存在");
        }
        // 2.比较密码 如果成功返回 UserDetails
        String encode = passwordEncoder.encode("123");
        if (passwordEncoder.matches("123", encode)) {
            return new User(username,encode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user"));
        }else {
            throw new UsernameNotFoundException("密码错误");
        }
    }
}
