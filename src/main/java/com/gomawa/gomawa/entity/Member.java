package com.gomawa.gomawa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime regDate;
    @OneToOne
    @JoinColumn(name = "SETTING_ID")
    private Setting setting;
    @OneToMany(mappedBy = "member")
    private List<ShareItem> shareItemList = new ArrayList();

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
