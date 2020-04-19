package com.gomawa.gomawa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gomawa.gomawa.dto.CommentDto;
import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.dto.ShareItemDto;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHARE_ITEM_ID")
    private ShareItem shareItem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private Date regDate;

    public CommentDto entityToDto() {
        MemberDto memberDto = member.entityToDto();
        ShareItemDto shareItemDto = shareItem.entityToDto();

        CommentDto commentDto = new CommentDto(id, content, shareItemDto, memberDto, regDate);

        return commentDto;
    }
}
