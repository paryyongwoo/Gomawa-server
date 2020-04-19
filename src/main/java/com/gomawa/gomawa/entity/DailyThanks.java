package com.gomawa.gomawa.entity;

import com.gomawa.gomawa.dto.DailyThanksDto;
import com.gomawa.gomawa.dto.MemberDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
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
