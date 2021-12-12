package com.yueking.core.service;

import com.yueking.core.dao.repository.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Resource
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        // userDetails = this.userCache.getUserFromCache(username);
        // if (userDetails == null) {
        //     try {
        //         SysUsers sysUsers = userDao.findUserByName(username);
        //         if (sysUsers == null) {
        //             throw new UsernameNotFoundException(username);
        //         }
        //         userDetails = new SysUserDetails(sysUsers);
        //         System.out.println("==========load user:"+username);
        //     } catch (Exception e) {
        //         throw new UsernameNotFoundException(username);
        //     }
        //     userCache.putUserInCache(userDetails);
        // }
        return userDetails;
    }
}
