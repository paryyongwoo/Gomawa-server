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
}
