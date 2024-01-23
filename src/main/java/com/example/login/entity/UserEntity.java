package com.example.login.entity;
import com.example.login.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
public class UserEntity implements UserDetails, Serializable {
    private  static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name="name", length=200, nullable = false, unique=true, columnDefinition = "TEXT")
    private String username;

    @Column(name="email", length=200, nullable = false, unique= true, columnDefinition = "TEXT")
    private String email;

    @Column(name="password", length=200, nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name="cpf", length=11, nullable = false, unique= true, columnDefinition = "TEXT")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 60, nullable = false, columnDefinition = "TEXT")
    private Role role;

    public UserEntity(){
    }


    public UserEntity(int userId, String username, String email, String password, String cpf, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.role = role;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.role != null) {
            authorities.add(new SimpleGrantedAuthority(this.role.toString()));
        }

        return authorities;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
