package com.gomawa.gomawa.dto;

import java.util.Date;

public class NoticeItemDto {
    // 데이터베이스 pk
    private long id;
    // 공지 제목
    private String title;
    // 공지 본문
    private String dsc;
    // 날짜
    private Date regDate;

    public NoticeItemDto() {

    }

    public NoticeItemDto(long id, String title, String dsc, Date regDate) {
        this.id = id;
        this.title = title;
        this.dsc = dsc;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "NoticeItemDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dsc='" + dsc + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
