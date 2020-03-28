package com.gomawa.gomawa.service;

import com.gomawa.gomawa.entity.DailyThanks;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.repository.DailyThanksRepository;
import com.gomawa.gomawa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyThanksService {

    @Autowired
    private DailyThanksRepository dailyThanksRepository;

    @Autowired
    private MemberRepository memberRepository;

    /**
     * DailyThnaks를 저장하는 기능
     */
    public DailyThanks addDailyThanks(DailyThanks dailyThanks) {
        return dailyThanksRepository.save(dailyThanks);
    }
}
