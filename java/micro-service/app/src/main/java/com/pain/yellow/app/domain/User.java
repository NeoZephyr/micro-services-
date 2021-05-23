package com.pain.yellow.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pain.yellow.app.annotation.validator.ValidEmail;
import com.pain.yellow.app.util.constant.PatternConstants;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@With
@Builder
@Data
@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长 ID，唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String group;
    /**
     * 用户名
     */
    @NotNull
    @Size(max = 32)
    @Column(length = 32, unique = true, nullable = false)
    private String username;

    /**
     * 手机号
     */
    @NotNull
    @Pattern(regexp = PatternConstants.PATTERN_MOBILE)
    @Size(min = 11, max = 11)
    @Column(length = 11, unique = true, nullable = false)
    private String mobile;

    /**
     * 姓名
     */
    @NotNull
    @Size(max = 32)
    @Column(length = 32)
    private String name;

    /**
     * 是否激活，默认激活
     */
    @Builder.Default
    @NotNull
    @Column(nullable = false)
    private boolean enabled = true;

    /**
     * 账户是否未过期，默认未过期
     */
    @Builder.Default
    @NotNull
    @Column(name = "account_non_expired", nullable = false)
    private boolean accountNonExpired = true;

    /**
     * 账户是否未锁定，默认未锁定
     */
    @Builder.Default
    @NotNull
    @Column(name = "account_non_locked", nullable = false)
    private boolean accountNonLocked = true;

    /**
     * 密码是否未过期，默认未过期
     */
    @Builder.Default
    @NotNull
    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired = true;

    /**
     * 密码哈希
     */
    @JsonIgnore
    @NotNull
    @Size(min = 40, max = 80)
    @Column(name = "password_hash", length = 80, nullable = false)
    private String password;

    /**
     * 电邮地址
     */
    @ValidEmail
    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true, nullable = false)
    private String email;

    /**
     * 是否启用两步验证
     */
    @Builder.Default
    @NotNull
    @Column(name = "using_mfa", nullable = false)
    private boolean usingMfa = false;

    /**
     * 两步验证的方式
     */
    @JsonIgnore
    @NotNull
    @Column(name = "mfa_key", nullable = false)
    private String mfaKey;

    /**
     * 角色列表，使用 Set 确保不重复
     */
//    @Builder.Default
//    @JsonIgnore
//    @ManyToMany
//    @Fetch(FetchMode.JOIN)
//    @JoinTable(
//            name = "mooc_users_roles",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    @BatchSize(size = 20)
//    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                .flatMap(role -> Stream.concat(
//                        Stream.of(new SimpleGrantedAuthority(role.getRoleName())),
//                        role.getPermissions().stream())
//                )
//                .collect(Collectors.toSet());
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", mfaKey='" + mfaKey + '\'' +
                "}";
    }
}
