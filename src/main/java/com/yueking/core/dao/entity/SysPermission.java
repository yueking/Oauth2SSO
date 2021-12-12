package com.yueking.core.dao.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class SysPermission implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "perm_id")
    private Long id;
    private String permName;
    private String permTag;
    private String permDesc;

    @Override
    public String getAuthority() {
        return this.permTag;
    }
}
