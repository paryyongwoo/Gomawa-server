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
public class Like {
    @Id @GeneratedValue
    @Column(name = "LIKE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "SHAREITEM_ID")
    private ShareItem shareItem;

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", member=" + member +
                ", shareItem=" + shareItem +
                '}';
    }
}
