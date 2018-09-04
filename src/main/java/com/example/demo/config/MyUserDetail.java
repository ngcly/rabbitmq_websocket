package com.example.demo.config;

import com.example.demo.dao.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetail extends User implements UserDetails {
    public MyUserDetail(User user) {
        if(user!=null){
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setName(user.getName());
            this.setPassword(user.getPassword());
            this.setGender(user.getGender());
            this.setAvatar(user.getAvatar());
            this.setEmail(user.getEmail());
            this.setLineState(user.getLineState());
            this.setSign(user.getSign());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
