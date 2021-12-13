package com.yueking.core.dao.repository;

import com.yueking.core.dao.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<SysRole, Long> {
}
