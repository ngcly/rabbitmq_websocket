package com.example.demo.config;

import com.example.demo.dao.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetail extends User implements UserDetails {
    public MyUserDetail(User user) {
        if(user!=null){
            this.setUsername(user.getUsername());
            this.setNickName(user.getNickName());
            this.setRealName(user.getRealName());
            this.setPassword(user.getPassword());
            this.setGender(user.getGender());
            this.setAvatar(user.getAvatar());
            this.setEmail(user.getEmail());
            this.setLineState(user.getLineState());
            this.setSignature(user.getSignature());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
