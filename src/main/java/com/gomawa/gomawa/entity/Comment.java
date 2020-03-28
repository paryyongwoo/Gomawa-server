package com.gomawa.gomawa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private long id;
    @Lob
    private String content;
    @ManyToOne
    @JoinColumn(name = "SHARE_ITME_ID")
    private ShareItem shareItem;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private Date regDate;
}
