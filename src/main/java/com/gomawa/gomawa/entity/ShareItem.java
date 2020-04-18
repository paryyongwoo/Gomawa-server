package com.gomawa.gomawa.entity;

import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.dto.ShareItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    // 임시로 현재 시각이 입력됨
    private Date regDate = new Date();

    // 좋아요
    // private int likeNum = 0;

    // 글쓴이
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 좋아요
    @OneToMany(mappedBy = "shareItem")
    private List<Likes> likeList = new ArrayList();

    // 댓글
    @OneToMany(mappedBy = "shareItem")
    private List<Comment> commentList = new ArrayList<>();

    // 업로드 이미지의 s3 url
    private String backgroundUrl;

    @Override
    public String toString() {
        return "ShareItem{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                //", likeNum=" + likeNum +
                '}';
    }

    public ShareItemDto entityToDto() {
        // 연관관계에 있는 Member 역시 DTO 로 변환
        MemberDto memberDto = member.entityToDto();

        return new ShareItemDto(id, memberDto, regDate, content, backgroundUrl, likeList.size());
    }
}