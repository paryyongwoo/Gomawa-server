package com.gomawa.gomawa.dto;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;

import java.util.Date;

public class ShareItemDto {
    // 데이터베이스 pk
    private long id;
    // 글쓴이 key
    // private long key;
    // 글작성 시간
    private Date date; // LocalDateTime이 지원하는 api레벨이 높아서 Date 객체를 사용.. String 고려..
    // 글내용
    private String content;
    // 이미지 주소
    private String backgroundUrl;
    // 좋아요
    private int likeNum;
    // 글쓴이
    private MemberDto member;

    public ShareItemDto() {

    }

//    public ShareItemDto(long id, long key, Date date, String content, String backgroundUrl, int like) {
//        this.id = id;
//        this.key = key;
//        this.date = date;
//        this.content = content;
//        this.backgroundUrl = backgroundUrl;
//        this.like = like;
//    }

    public ShareItemDto(long id, MemberDto memberDto, Date date, String content, String backgroundUrl, int likeNum) {
        this.id = id;
        this.member = memberDto;
        this.date = date;
        this.content = content;
        this.backgroundUrl = backgroundUrl;
        this.likeNum = likeNum;
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

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public MemberDto getMember() {
        return member;
    }

    public void setMember(MemberDto memberDto) {
        this.member = memberDto;
    }

    @Override
    public String toString() {
        return "ShareItemDto{" +
                "id=" + id +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                ", likeNum=" + likeNum +
                ", member=" + member +
                '}';
    }
}
