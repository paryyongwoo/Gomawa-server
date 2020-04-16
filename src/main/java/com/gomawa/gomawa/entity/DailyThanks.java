package com.gomawa.gomawa.entity;

import com.gomawa.gomawa.dto.DailyThanksDto;
import com.gomawa.gomawa.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
public class DailyThanks {

    @Id @GeneratedValue
    private Long id;
    @Lob
    private String content1;
    @Lob
    private String content2;
    @Lob
    private String content3;
    private Date regDate;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member regMember;

    public DailyThanks(String content1, String content2, String content3, Date regDate, Member regMember) {
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.regDate = regDate;
        this.regMember = regMember;
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Member getRegMember() {
        return regMember;
    }

    public void setRegMember(Member member) {
        this.regMember = member;
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

    public DailyThanksDto convertToDto() {
        MemberDto memberDto = this.regMember.entityToDto();

        return new DailyThanksDto(this.id, this.content1, this.content2, this.content3, this.regDate, memberDto);
    }
}
