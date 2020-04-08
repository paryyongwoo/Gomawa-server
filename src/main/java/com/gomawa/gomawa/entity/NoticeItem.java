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
public class NoticeItem {

    @Id @GeneratedValue
    @Column(name = "NOTICEITEM_ID")
    private Long id;
    private String title;
    private String text;
    private String date;
}
