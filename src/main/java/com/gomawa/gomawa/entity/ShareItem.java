package com.gomawa.gomawa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ShareItem {
    @Id @GeneratedValue
    @Column(name = "SHARE_ITEM_ID")
    private Long id;
    @Column(columnDefinition = "text")
    private String content;
    private LocalDateTime regDate = LocalDateTime.now();
    private int likeNum = 0;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private String backgroundUrl;

    @Override
    public String toString() {
        return "ShareItem{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                ", likeNum=" + likeNum +
                '}';
    }
}
