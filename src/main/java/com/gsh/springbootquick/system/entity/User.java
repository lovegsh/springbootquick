package com.gsh.springbootquick.system.entity;

import lombok.*;
import org.hibernate.engine.profile.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author GSH
 * @create 2023/1/24 16:51
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "system_user")
public class User extends BaseEntity implements UserDetails {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 性别 0-男 1-女
     */
    @Column
    private Integer gender;
    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 用于密码加密的盐值
     */
    @Column
    private String salt;

    /**
     * 身份证号
     */
//    @Column(name = "id_card")
    @Column
    private String idCard;

    /**
     * 邮箱
     */
    @Column
    private String email;

    /**
     * 电话
     */
    @Column
    private String phone;

    /**
     * 生日
     */
    @Column
    private LocalDateTime birthday;

    @Column
    private Integer age;

    /**
     * 状态 1-正常 2-禁用
     */
    @Column
    private Integer status;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", status=" + status +
                ", lastLogin=" + lastLogin +
                "} " + super.toString();
    }

    /**
     * 用户权限
     */
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否处于启用状态
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}



