package com.example.demo.dao.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User extends DateAudit {

    @Id
    @NotBlank
    @Size(max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    private String nickName;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    private String realName;

    @Column(columnDefinition="enum('男','女')")
    private String gender;

    private String signature;

    private String avatar;

    private String telPhone;

    private String lineState;

    public User() {

    }

    public User(String nickName, String username, String email, String password) {
        this.nickName = nickName;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLineState() {
        return lineState;
    }

    public void setLineState(String lineState) {
        this.lineState = lineState;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }
}
