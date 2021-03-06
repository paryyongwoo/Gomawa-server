package com.gomawa.gomawa.dto;

import java.util.Date;

public class MemberDto {
    private Long id;
    // 네이버, 카카오 로그인에서 제공하는 식별자 id
    private Long key;
    // 이메일
    private String email;
    // 성별
    private String gender;
    // 닉네임
    private String nickName;
    // 최초 로그인 시각
    private Date regDate = new Date();
    // 프로필 이미지 url
    private String profileImgUrl;

    public MemberDto() {

    }

    public MemberDto(Long id, Long key, String email, String gender, String nickName, Date regDate, String profileImgUrl) {
        this.id = id;
        this.key = key;
        this.email = email;
        this.gender = gender;
        this.nickName = nickName;
        this.regDate = regDate;
        this.profileImgUrl = profileImgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProfileImgUrl() {
        return profileImgUrl;
    }

    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }

    @Override
    public String toString() {
        return "Member{" +
                "key=" + key +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", regDate=" + regDate +
                ", profileImgUrl='" + profileImgUrl + '\'' +
                '}';
    }
}
