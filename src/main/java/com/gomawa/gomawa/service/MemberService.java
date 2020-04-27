package com.gomawa.gomawa.service;

import com.gomawa.gomawa.aws.S3Service;
import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ShareItemRepository shareItemRepository;

    @Autowired
    S3Service s3Service;

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

    /**
     * 1. Key 값으로 DB 에서 Member 를 가져오고
     * 2. Member 가 있다면 그 Member 를 반환,
     * 3. Member 가 없다면 클라이언트에서 받아온 Member 를 DB 에 INSERT 하는 메소드
     */
    public Member addMemberOnStart(Long key, Member memberParam) {
        Member member = getMemberByKey(key);

        if (member == null) {
            // 위 Key 값으로 가입된 Member 가 없다면 ~ 매개변수로 받아온 Member를 DB에 추가하고, 해당 Member 를 반환
            member = memberRepository.save(memberParam);
        }

        return member;
    }

    /**
     * 1. Key 값으로 DB 에서 Member 를 가져오고
     * 2. 가져온 Member 의 NickName 을 변경하고
     * 3. DB 에 UPDATE 하는 메소드
     */
    public Member setNickName(Long key, String nickName) {
        Member member = getMemberByKey(key);

        if(member == null) {
            // todo: DB에서 가져온 멤버가 null 일 때 예외 처리
        } else {
            member.setNickName(nickName);

            memberRepository.save(member);
        }

        return member;
    }

    public Member updateMemberProfileImageUrl(MultipartFile file, String items) throws Exception {
        JSONObject jsonObject = new JSONObject(items);

        // Member ID
        String memberIdString = jsonObject.getString("id");
        Long memberId = Long.parseLong(memberIdString);

        // Member Entity
        Member member = memberRepository.findById(memberId).orElse(null);
        if(member == null) { throw new Exception("member is null"); }

        // Member Profile Image Url
        String url = s3Service.upload(file);

        // Member Entity Profile Image Url 수정
        member.setProfileImgUrl(url);

        // 수정된 Entity 를 INSERT
        memberRepository.save(member);

        return member;
    }

    /**
     * 1. Key 값으로 DB 에서 Member 를 가져오고
     * 2. 가져온 Member 를 매개변수로 Delete 하는 메소드
     */
    public void deleteMemberByKey(Long key) throws Exception {
        Member member = getMemberByKey(key);

        if(member == null) {
            throw new Exception("해당 Key 값의 Member 가 없습니다.");
        } else {
            memberRepository.delete(member);
        }
    }
}
