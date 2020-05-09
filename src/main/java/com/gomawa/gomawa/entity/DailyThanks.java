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
    private String content;
    private Date regDate = new Date();
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member regMember;

    public DailyThanks(String content, Date regDate, Member regMember) {
        this.content = content;
        this.regDate = regDate;
        this.regMember = regMember;
    }

    @Override
    public String toString() {
        return "DailyThanks{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public DailyThanksDto convertToDto() {
        MemberDto memberDto = this.regMember.entityToDto();

        return new DailyThanksDto(this.id, this.content, this.regDate, memberDto);
    }
}
