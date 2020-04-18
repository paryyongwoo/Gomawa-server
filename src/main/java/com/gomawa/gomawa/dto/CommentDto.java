package com.gomawa.gomawa.dto;

import com.gomawa.gomawa.entity.ShareItem;

import java.util.Date;

public class CommentDto {
    // 데이터베이스 pk
    private long id;
    // 댓글 내용
    private String content;
    // 소속된 글
    private ShareItemDto shareItem;
    // 댓글 쓴 멤버
    private MemberDto member;
    // 날짜
    private Date regDate;

    public CommentDto() {

    }

    public CommentDto(long id, String content, ShareItemDto shareItem, MemberDto member, Date regDate) {
        this.id = id;
        this.content = content;
        this.shareItem = shareItem;
        this.member = member;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ShareItemDto getShareItem() {
        return shareItem;
    }

    public void setShareItem(ShareItemDto shareItem) {
        this.shareItem = shareItem;
    }

    public MemberDto getMember() {
        return member;
    }

    public void setMember(MemberDto member) {
        this.member = member;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", shareItem=" + shareItem +
                ", member=" + member +
                ", regDate=" + regDate +
                '}';
    }
}
