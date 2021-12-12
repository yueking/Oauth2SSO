package com.yueking.core.dao.repository;

import com.yueking.core.dao.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<SysUser, Long> {
}
