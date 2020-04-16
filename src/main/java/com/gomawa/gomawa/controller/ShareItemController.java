package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.ShareItemDto;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.service.ShareItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(
        produces = MediaTypes.HAL_JSON_VALUE
        //,consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ShareItemController {

    @Autowired
    private ShareItemService shareItemService;

    // GET
    @RequestMapping(
            value = "/api/shareItem",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<ShareItemDto>> getShareItemAll() {
        // ShareItem Entity List
        List<ShareItem> shareItems = shareItemService.getShareItemAll();
        int size = shareItems.size();

        // 반환될 ShareItem DTO List
        List<ShareItemDto> shareItemDtos = new ArrayList<ShareItemDto>();

        // ShareItem Entity -> DTO 변환
        for(int i=0; i<size; i++) {
            shareItemDtos.add(shareItems.get(i).entityToDto());
        }

        return ResponseEntity.ok(shareItemDtos);
    }

    // POST
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
