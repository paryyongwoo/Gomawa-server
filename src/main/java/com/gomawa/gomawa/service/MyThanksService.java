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
     * 접속한 사용자의 당일 DailyThanks를 가져오는 기능
     * @param memberId 접속한 사용자의 id
     * @return 당일 작성된 DailyThanks
     */
    public DailyThanksDto getDailyThanks(Long memberId) {
        Optional<Member> optional = memberRepository.findById(memberId);
        Member member = optional.orElseThrow();
        DailyThanks dailyThanks = dailyThanksRepository.findByRegMember(member);
        DailyThanksDto dailyThanksDto = dailyThanks.convertToDto();
        return dailyThanksDto;
    }
    /**
     * DailyThnaks를 저장하는 기능
     */
    public DailyThanksDto addDailyThanks(DailyThanksDto dailyThanksDto) {
        MemberDto memberDto = dailyThanksDto.getRegMember();
        Optional<Member> memberOptional = memberRepository.findByKey(memberDto.getKey());
        Member member = memberOptional.orElseThrow();

        String content = dailyThanksDto.getContent();
        Date regDate = dailyThanksDto.getRegDate();

        DailyThanks dailyThanks = new DailyThanks(content, regDate, member);
        dailyThanksRepository.save(dailyThanks);

        DailyThanksDto savedDailyThanksDto = dailyThanks.convertToDto();
        return savedDailyThanksDto;
    }
}
