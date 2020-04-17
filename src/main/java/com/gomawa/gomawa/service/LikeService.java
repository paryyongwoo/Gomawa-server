package com.gomawa.gomawa.service;

import com.gomawa.gomawa.entity.Like;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.repository.LikeRepository;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ShareItemRepository shareItemRepository;


    public ShareItem updateLike(Long memberKey, Long shareItemId) {
        Member member = memberRepository.findByKey(memberKey).orElse(null);
        if(member == null) {
            System.out.println("member is null");
        }

        ShareItem shareItem = shareItemRepository.findById(shareItemId).orElse(null);
        if(shareItem == null) {
            System.out.println("member is null");
        }

        Like like = likeRepository.findByMemberAndShareItem(member, shareItem).orElse(null);
        if(like == null) {
            like = new Like();
            like.setMember(member);
            like.setShareItem(shareItem);

            likeRepository.save(like);
        } else {
            likeRepository.delete(like);
        }

        shareItem = shareItemRepository.findById(shareItemId).orElse(null);
        if(shareItem == null) {
            System.out.println("member is null");
        }

        return shareItem;
    }
}
