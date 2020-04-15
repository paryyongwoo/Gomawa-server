package com.gomawa.gomawa.service;

import com.gomawa.gomawa.aws.S3Service;
import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.dto.ShareItemDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class ShareItemService {

    @Autowired
    private ShareItemRepository shareItemRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private S3Service s3Service;

    public ShareItemDto addShareItem(MultipartFile file, String items) throws Exception {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            System.out.println("fileName = " + fileName + ", items: " + items);

            /**
             * items를 json으로 파싱해서 key, content를 가져옴
             */
            JSONObject jsonObject = new JSONObject(items);
            String keyString = jsonObject.getString("key");
            if (StringUtils.isEmpty(keyString)) {
                throw new Exception("글쓴이 정보가 없음");
            }
            Long key = Long.parseLong(keyString);
            String content = jsonObject.getString("content");

            // 글쓴이 정보 가져오기
            Member member = memberService.getMemberByKey(key);

            /**
             * 1. 업로드 이미지 파일을 File로 받아서 s3에 업로드
             * 2. s3에서 받은 업로드 파일의 주소를 db에 저장
             */
            // TODO: 2020/04/15 파일 이름 설정
            String uploadUrl = s3Service.upload(file);
            System.out.println("uploadUrl = " + uploadUrl);

            // ShareItem 객체 생성
            ShareItem shareItem = new ShareItem();
            shareItem.setContent(content);
            shareItem.setMember(member);
            shareItem.setBackgroundUrl(uploadUrl);

            // shareItem 테이블 저장
            shareItemRepository.save(shareItem);

            // ResponseBody에 넣어서 보내줄 데이터 설정
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail(member.getEmail());
            memberDto.setNickName(member.getNickName());
            ShareItemDto shareItemDto = new ShareItemDto();
            shareItemDto.setMemberDto(memberDto);

            return shareItemDto;
    }

    public ShareItem addLike(long id) throws Exception {
        Member dummyMember = new Member(1l, 123456l, "ektto1041@park.sang", "man", "mrPark", new Date(), null, null);
        ShareItem dummyShareItem = new ShareItem(1l, "this is content", new Date(), 3, dummyMember, "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory&fname=https://k.kakaocdn.net/dn/EShJF/btquPLT192D/SRxSvXqcWjHRTju3kHcOQK/img.png");

        // ID 값으로 게시글을 가져옴.
        // ShareItem shareItem = shareItemRepository.findById(id).orElse(null);
        ShareItem shareItem = dummyShareItem;

        if(shareItem == null) { throw new Exception("해당 게시글이 존재하지 않습니다."); }

        // 현재 좋아요 수
        int likeNum = shareItem.getLikeNum();
        shareItem.setLikeNum(likeNum + 1);

        // 다시 DB 에 저장시킴 ( DB 에서 가져온 Entity 여서 UPDATE 됨 )
        shareItemRepository.save(shareItem);

        // 좋아요 수가 1 증가된 ShareItem 이 반환됨
        return shareItem;
    }
}
