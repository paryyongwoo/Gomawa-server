package com.gomawa.gomawa.service;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ShareItemRepository shareItemRepository;

    /**
     * GET
     */
    // Key 값으로 Member를 반환하는 메소드
    public Member getMemberByKey(Long key) {
        Optional<Member> optional = memberRepository.findByKey(key);

        // 레코드가 없으면 Null 반환
        return optional.orElse(null);
    }

    /**
     * 새로운 회원을 추가해주는 메소드
     * @param member 생성할 유저
     * @return 생성된 유저
     */
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
}
