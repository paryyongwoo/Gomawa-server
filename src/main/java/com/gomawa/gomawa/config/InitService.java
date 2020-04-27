package com.gomawa.gomawa.config;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;

//@Service
public class InitService implements CommandLineRunner {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ShareItemRepository shareItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Member member = new Member();
        member.setEmail("admin@kakao.com");
        member.setKey(6l);
        member.setNickName("admin");

        memberRepository.save(member);

        for (int i = 0; i < 100; i ++) {
            ShareItem shareItem = new ShareItem();
            shareItem.setMember(member);
            shareItem.setContent(i + "번째 글입니다.");
            shareItem.setRegDate(new Date());

            shareItemRepository.save(shareItem);
        }

    }
}
