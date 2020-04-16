package com.gomawa.gomawa.service;

import com.gomawa.gomawa.dto.DailyThanksDto;
import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.entity.DailyThanks;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.repository.DailyThanksRepository;
import com.gomawa.gomawa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

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
    public DailyThanksDto addDailyThanks(DailyThanksDto dailyThanksDto) {
        MemberDto memberDto = dailyThanksDto.getRegMember();
        Optional<Member> memberOptional = memberRepository.findByKey(memberDto.getKey());
        Member member = memberOptional.orElseThrow();

        String content1 = dailyThanksDto.getContent1();
        String content2 = dailyThanksDto.getContent2();
        String content3 = dailyThanksDto.getContent3();
        Date regDate = dailyThanksDto.getRegDate();

        DailyThanks dailyThanks = new DailyThanks(content1, content2, content3, regDate, member);
        dailyThanksRepository.save(dailyThanks);

        DailyThanksDto savedDailyThanksDto = dailyThanks.convertToDto();
        return savedDailyThanksDto;
    }
}
