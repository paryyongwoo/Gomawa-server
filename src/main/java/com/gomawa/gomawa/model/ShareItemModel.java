package com.gomawa.gomawa.model;

import com.gomawa.gomawa.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareItemModel extends RepresentationModel<ShareItemModel> {

    private Long id;
    private String content;
    private LocalDateTime regDate;
    private int likeNum;
    private Member member;
    private String backgroundUrl;

}
