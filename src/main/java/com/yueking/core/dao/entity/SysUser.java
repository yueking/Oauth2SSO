package com.yueking.core.dao.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class SysUser implements UserDetails, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public List<SysRole> roles = new LinkedList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List authorities = new LinkedList();
        for (SysRole role : roles) {
            authorities.add(role);
            List<SysPermission> permissions = role.getPermissions();
            for (SysPermission permission : permissions) {
                authorities.add(permission);
            }
        }
        return authorities;
    }

    public void addRole(SysRole role) {
        this.roles.add(role);
    }

    public void removeRole(SysRole role) {
        this.roles.remove(role);
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}