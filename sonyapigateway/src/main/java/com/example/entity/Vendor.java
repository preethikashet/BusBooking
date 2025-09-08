package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendortable")
public class Vendor implements UserDetails {

    @Id
    @NotNull(message = "Id cannot be null")
    private Integer vendorid;

    @Column(nullable = false)
    private String vendorname;

    @Size(min = 10, message = "Phone number must have at least 10 digits")
    @Column(nullable = false)
    private String phoneno;

    @Email(message = "Must be a valid email")
    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotNull(message = "Password cannot be null")
    @Column(nullable = false)
    private String password;

    // ✅ No roles, so we return an empty list
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        // Using email as the username
        return email;
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
