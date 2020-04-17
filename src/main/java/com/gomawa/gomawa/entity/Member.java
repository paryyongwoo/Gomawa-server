package com.gomawa.gomawa.entity;

import com.gomawa.gomawa.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private Long key;
    private String email;
    private String gender;
    private String nickName;
    private Date regDate;
    private String profileImgUrl;

    @OneToOne
    @JoinColumn(name = "SETTING_ID")
    private Setting setting;
    @OneToMany(mappedBy = "member")
    private List<ShareItem> shareItemList = new ArrayList();
    @OneToMany(mappedBy = "member")
    private List<Like> likeList = new ArrayList();

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", nickName='" + nickName + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public MemberDto entityToDto() {
        return new MemberDto(key, email, gender, nickName, regDate, profileImgUrl);
    }
}
