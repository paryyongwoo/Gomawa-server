package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.aws.S3Service;
import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.dto.ShareItemDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.service.MemberService;
import com.gomawa.gomawa.service.ShareItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(
        produces = MediaTypes.HAL_JSON_VALUE
        //,consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ShareItemController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShareItemService shareItemService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private S3Service s3Service;

    /**
     * @param file 업로드 이미지 파일
     * @param items 글내용, 작성자의 key가 담겨있는 json형식의 문자열
     */
    @RequestMapping(
            value = "/api/shareItem",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ShareItemDto> addShareItem(@RequestParam("file") MultipartFile file, @RequestParam String items) {

        try {
            ShareItemDto shareItemDto = shareItemService.addShareItem(file, items);

            /**
             * ShareItem에는 Member타입의 member라는 필드가 존재한다.
             * 이를 ResponseBody에 넣어서 클라이언트로 보내면
             * 클라이언트의 ShareItem은 Member를 가지고 있지 않음.. 어떻게 받는가
             */
            return ResponseEntity.ok(shareItemDto);
        } catch (Exception e ) {
            e.printStackTrace();
            // TODO: 2020/04/15 메시지 처리
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(
            value = "/api/shareItem/like/{id}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<ShareItemDto> addLike(@PathVariable Long id) {
        try {
            // 반환할 데이터가 담긴 ShareItem Entity
            ShareItem shareItemEntity = shareItemService.addLike(id);

            ShareItemDto shareItemDto = shareItemEntity.entityToDto();

            return ResponseEntity.ok(shareItemDto);
        } catch(Exception e) {
            // TODO: 2020-04-15 addLike 예외 발생 시 처리
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
    }
}
