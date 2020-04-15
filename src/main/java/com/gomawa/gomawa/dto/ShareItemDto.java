package com.gomawa.gomawa.dto;

import java.util.Date;

public class ShareItemDto {
    // 데이터베이스 pk
    private long id;
    // 글쓴이 key
    private long key;
    // 글작성 시간
    private Date date; // LocalDateTime이 지원하는 api레벨이 높아서 Date 객체를 사용.. String 고려..
    // 글내용
    private String content;
    // 이미지 주소
    private String backgroundUrl;
    // 좋아요
    private int like;
    private MemberDto memberDto;

    public ShareItemDto() {

    }

    public ShareItemDto(long id, long key, Date date, String content, String backgroundUrl, int like) {
        this.id = id;
        this.key = key;
        this.date = date;
        this.content = content;
        this.backgroundUrl = backgroundUrl;
        this.like = like;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }
}
