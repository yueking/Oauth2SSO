package com.yueking.authorizationserver.core.dao.repository;

import com.yueking.authorizationserver.core.dao.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<SysUser, String> {
}
