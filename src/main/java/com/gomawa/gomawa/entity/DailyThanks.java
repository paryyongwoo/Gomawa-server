package com.gomawa.gomawa.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DailyThanks {

    @Id @GeneratedValue
    private Long id;
    @Lob
    private String content1;
    @Lob
    private String content2;
    @Lob
    private String content3;
    private LocalDateTime regDate;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public DailyThanks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "DailyThanks{" +
                "id=" + id +
                ", content1='" + content1 + '\'' +
                ", content2='" + content2 + '\'' +
                ", content3='" + content3 + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
