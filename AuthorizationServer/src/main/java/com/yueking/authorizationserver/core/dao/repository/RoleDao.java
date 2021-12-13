package com.yueking.authorizationserver.core.dao.repository;

import com.yueking.authorizationserver.core.dao.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<SysRole, Long> {
}
