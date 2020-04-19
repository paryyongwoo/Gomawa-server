package com.gomawa.gomawa.entity;

import com.gomawa.gomawa.dto.NoticeItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class NoticeItem {

    @Id @GeneratedValue
    @Column(name = "NOTICEITEM_ID")
    private Long id;
    private String title;
    private String dsc;
    private Date regDate;

    public NoticeItemDto entityToDto() {
        return new NoticeItemDto(id, title, dsc, regDate);
    }
}
