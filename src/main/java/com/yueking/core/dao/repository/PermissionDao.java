package com.yueking.core.dao.repository;

import com.yueking.core.dao.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<SysPermission,Integer> {
}
