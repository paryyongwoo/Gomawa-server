package com.gomawa.gomawa.service;

import com.gomawa.gomawa.aws.S3Service;
import com.gomawa.gomawa.dto.ShareItemDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.LikeRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShareItemService {

    @Autowired
    private ShareItemRepository shareItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private S3Service s3Service;

    public List<ShareItemDto> getShareItemAll(Long memberId) {
        // 반환될 ShareItem DTO List
        List<ShareItemDto> shareItemDtos = new ArrayList<>();

        List<ShareItem> shareItems = shareItemRepository.findAll();
        for (ShareItem shareItem : shareItems) {
            Long shareItemId = shareItem.getId();
            boolean isLike = likeRepository.existsLikesByMemberIdAndShareItemId(memberId, shareItemId);
            System.out.println("isLike = " + isLike);
            ShareItemDto shareItemDto = shareItem.entityToDto();
            shareItemDto.setIsLike(isLike);
            shareItemDtos.add(shareItemDto);
        }

        return shareItemDtos;
    }

    public List<ShareItemDto> getShareItemByMemberKey(Long memberKey) throws Exception {
        // Member Key 로 Member 가져오기
        Member member = memberRepository.findByKey(memberKey).orElse(null);
        if(member == null) { throw new Exception("member is null"); }

        // TODO: 2020-04-20 memberKey -> memberId
        Long memberId = member.getId();

        // Member 로 ShareItem List 가져오기
        List<ShareItem> shareItemList = shareItemRepository.findAllByMember(member);
        int size = shareItemList.size();



        // DTO 변환
        List<ShareItemDto> shareItemDtoList = new ArrayList<>();
        for(int i=0; i<size; i++) {
            Long shareItemId = shareItemList.get(i).getId();
            // isLike
            boolean isLike = likeRepository.existsLikesByMemberIdAndShareItemId(memberId, shareItemId);
            
            ShareItemDto shareItemDto = shareItemList.get(i).entityToDto();
            shareItemDto.setIsLike(isLike);

            shareItemDtoList.add(shareItemDto);
        }

        return shareItemDtoList;
    }

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
            String uploadUrl = null;
            if (file.getSize() != 0) {
                /**
                 * 업로드하려는 이미지가 있는 경우
                 */
                uploadUrl = s3Service.upload(file);
            }

            // ShareItem 객체 생성
            ShareItem shareItem = new ShareItem();
            shareItem.setContent(content);
            shareItem.setMember(member);
            shareItem.setBackgroundUrl(uploadUrl);
            shareItem.setRegDate(new Date());

            // shareItem 테이블 저장
            shareItemRepository.save(shareItem);

            // ShareItem Entity 를 DTO 로 변경
            ShareItemDto shareItemDto = shareItem.entityToDto();

            return shareItemDto;
    }
}
