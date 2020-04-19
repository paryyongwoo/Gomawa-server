package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.NoticeItemDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.NoticeItem;
import com.gomawa.gomawa.service.MemberService;
import com.gomawa.gomawa.service.NoticeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE }
        )
public class NoticeItemController {

    @Autowired
    private NoticeItemService noticeItemService;

    // GET
    @RequestMapping(
            value = "/api/notice",
            method = RequestMethod.GET
    ) // 모든 공지사항을 가져오는 메소드
    public ResponseEntity<List<NoticeItemDto>> getNoticeAll() {
        try {
            List<NoticeItemDto> noticeItemDtoList = noticeItemService.getNoticeAll();

            return ResponseEntity.ok(noticeItemDtoList);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
    }
}