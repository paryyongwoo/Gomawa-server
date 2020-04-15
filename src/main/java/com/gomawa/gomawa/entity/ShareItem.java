package com.gomawa.gomawa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 게시글 엔터티
 */
@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ShareItem {

    @Id @GeneratedValue
    @Column(name = "SHARE_ITEM_ID")
    private Long id;

    @Column(columnDefinition = "text")
    private String content;

    // 등록일
    private LocalDateTime regDate = LocalDateTime.now();

    // 좋아요
    private int likeNum = 0;

    // 글쓴이
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 업로드 이미지의 s3 url
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
