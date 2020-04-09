package com.gomawa.gomawa.service;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.NoticeItem;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.NoticeItemRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoticeItemService {

    @Autowired
    NoticeItemRepository noticeItemRepository;

    /**
     * GET
     */
    //
    public List<NoticeItem> getNoticeAll() throws Exception {
        // 공지사항을 추가하는 기능이 없으니, 여기서 가짜 공지사항을 만들어 DB에 저장함
        NoticeItem noticeItem = new NoticeItem();
        noticeItem.setTitle("공지사항입니다!!");
        noticeItem.setDsc("만나뵙게 되어 영광입니다\n잘 부탁합니다.");
        noticeItem.setDate("1996년 6월 12일");
        noticeItemRepository.save(noticeItem);

        List<NoticeItem> noticeItems = noticeItemRepository.findAll();

        // TODO: 2020-04-08 예외 처리
        if (noticeItems == null) {
            throw new Exception("noticeItems is null");
        }

        return noticeItems;
    }
}
