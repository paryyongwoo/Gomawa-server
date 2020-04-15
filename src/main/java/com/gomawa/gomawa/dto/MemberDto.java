package com.gomawa.gomawa.dto;

import com.gomawa.gomawa.entity.Member;

import java.time.LocalDateTime;
import java.util.Date;

public class MemberDto {
    private Long key; // 네이버, 카카오 로그인에서 제공하는 식별자 id
    private String email;
    private String gender;
    private String nickName; // 닉네임
    private Date regDate;

    public MemberDto() {

    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "key=" + key +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public MemberDto(Long key, String email, String gender, String nickName, Date regDate) {
        this.key = key;
        this.email = email;
        this.gender = gender;
        this.nickName = nickName;
        this.regDate = regDate;
    }
}
