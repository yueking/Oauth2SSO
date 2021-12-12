package com.yueking.core;

import com.yueking.core.dao.entity.SysPermission;
import com.yueking.core.dao.entity.SysRole;
import com.yueking.core.dao.entity.SysUser;
import com.yueking.core.dao.repository.PermissionDao;
import com.yueking.core.dao.repository.RoleDao;
import com.yueking.core.dao.repository.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.Collection;

@SpringBootTest
public class SpringBootJpaTester {
    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void jpa(){
        System.out.println("jpa...");
        SysPermission permission = new SysPermission();
        permission.setPermName("perm1");
        permission.setPermTag("perm1Tag");
        permission.setPermDesc("perm1Desc");
        permissionDao.saveAndFlush(permission);
    }

    @Test
    void addRole(){
        SysRole role1 = new SysRole();
        role1.setRoleName("roleName1");
        role1.setRoleTag("roleTag1");
        role1.setRoleDesc("roleDesc1");

        roleDao.saveAndFlush(role1);
    }

    @Test
    void modifyRole(){
        long roleId = 1;
        SysRole role = roleDao.findById(roleId).get();

        System.out.println(role);

        SysPermission p1 = permissionDao.findById(1l).get();
        SysPermission p2 = permissionDao.findById(2l).get();

        role.addPermission(p1);
        role.addPermission(p2);

        roleDao.saveAndFlush(role);

    }

    @Test
    void createRole() {
        SysRole role = new SysRole();
        role.setRoleName("roleName2");
        role.setRoleTag("roleTag2");
        role.setRoleDesc("roleDesc2");

        SysPermission p3 = permissionDao.findById(3l).get();
        SysPermission p4 = permissionDao.findById(4l).get();

        role.addPermission(p3);
        role.addPermission(p4);

        roleDao.saveAndFlush(role);
    }

    @Test
    void addAdminUser() {
        SysUser admin = new SysUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));

        SysUser user = new SysUser();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));

        SysRole adminRole = roleDao.findById(1l).get();
        SysRole userRole = roleDao.findById(2l).get();

        admin.addRole(adminRole);
        user.addRole(userRole);

        userDao.saveAndFlush(admin);
        userDao.saveAndFlush(user);
    }

    @Test
    void showUser(){
        SysUser sysUser = userDao.findById(1l).get();
        Collection<? extends GrantedAuthority> authorities = sysUser.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
        }
    }
}
