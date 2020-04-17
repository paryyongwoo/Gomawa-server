package com.gomawa.gomawa.service;

import com.gomawa.gomawa.entity.Likes;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.repository.LikeRepository;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ShareItemRepository shareItemRepository;

    // 좋아요 데이터가 없으면 추가하고, 있으면 제거하는 메소드
    public ShareItem updateLike(Long memberKey, Long shareItemId) throws Exception {
        // Member Key 값으로 Member 가져옴
        Member member = memberRepository.findByKey(memberKey).orElse(null);
        if(member == null) { throw new Exception("findByKey(memberKey) return null"); }

        // ShareItem Id 값으로 ShareItem 가져옴
        ShareItem shareItem = shareItemRepository.findById(shareItemId).orElse(null);
        if(shareItem == null) { throw new Exception("findById(shareItemId) return null"); }

        // 위 두 데이터 가지고 있는 레코드를 검사, 그런 레코드가 있다면 ~ 이미 좋아요를 눌렀다는 의미
        Likes like = likeRepository.findByMemberAndShareItem(member, shareItem).orElse(null);
        if(like == null) {
            // 좋아요가 눌러져 있지 않다면 DB 에 새 like 레코드 추가
            like = new Likes();
            like.setMember(member);
            like.setShareItem(shareItem);

            likeRepository.save(like);
        } else {
            // 좋아요가 눌러져 있다면 해당 like 레코드 제거
            likeRepository.delete(like);
        }

        // 수정된 좋아요의 Count 가 포함된 ShareItem Entity - 클라이언트에서 View 의 설정을 위해
        shareItem = shareItemRepository.findById(shareItemId).orElse(null);
        if(shareItem == null) {
            System.out.println("member is null");
        }

        return shareItem;
    }
}
