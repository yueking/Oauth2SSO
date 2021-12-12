package com.yueking.core;

import com.yueking.core.dao.entity.SysPermission;
import com.yueking.core.dao.repository.PermissionDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SpringBootJpaTester {
    @Resource
    private PermissionDao permissionDao;
    @Test
    void jpa(){
        System.out.println("jpa...");
        SysPermission permission = new SysPermission();
        permission.setPermName("perm1");
        permission.setPermTag("perm1Tag");
        permission.setPermDesc("perm1Desc");
        permissionDao.saveAndFlush(permission);
    }
}
