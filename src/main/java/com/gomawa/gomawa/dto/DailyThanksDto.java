package com.gomawa.gomawa.dto;

import java.util.Date;

public class DailyThanksDto {

    private Long id;
    private String content;
    private Date regDate;
    private MemberDto regMember;

    public DailyThanksDto() {

    }

    public DailyThanksDto(Long id, String content, Date regDate, MemberDto regMember) {
        this.id = id;
        this.content = content;
        this.regDate = regDate;
        this.regMember = regMember;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public MemberDto getRegMember() {
        return regMember;
    }

    public void setRegMember(MemberDto regMember) {
        this.regMember = regMember;
    }

    @Override
    public String toString() {
        return "DailyThanksDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                ", regMember=" + regMember +
                '}';
    }
}
