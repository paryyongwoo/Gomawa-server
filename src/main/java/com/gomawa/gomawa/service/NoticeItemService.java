package com.gomawa.gomawa.service;

import com.gomawa.gomawa.dto.NoticeItemDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.NoticeItem;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.NoticeItemRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class NoticeItemService {

    @Autowired
    NoticeItemRepository noticeItemRepository;

    /**
     * GET
     */
    //
    public List<NoticeItemDto> getNoticeAll() throws Exception {
        // 공지사항을 추가하는 기능이 없으니, 여기서 가짜 공지사항을 만들어 DB에 저장함
        NoticeItem noticeItem = new NoticeItem();
        noticeItem.setTitle("공지사항입니다!!");
        noticeItem.setDsc("만나뵙게 되어 영광입니다\n잘 부탁합니다.");
        noticeItem.setRegDate(new Date());
        noticeItemRepository.save(noticeItem);

        // DB 에서 모든 공지사항을 가져옴
        List<NoticeItem> noticeItemList = noticeItemRepository.findAll();
        int size = noticeItemList.size();

        // DTO 로 변환
        List<NoticeItemDto> noticeItemDtoList = new ArrayList<>();
        for(int i=0; i<size; i++) {
            noticeItemDtoList.add(noticeItemList.get(i).entityToDto());
        }

        return noticeItemDtoList;
    }
}
