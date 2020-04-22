package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.ShareItemDto;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.service.ShareItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            value = "/api/shareItems/{memberId}/{page}",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<ShareItemDto>> getShareItemAll(@PathVariable("memberId") Long memberId, @PathVariable("page") int page) {
        // ShareItem Entity List
        List<ShareItemDto> shareItemDtoList = shareItemService.getShareItemAll(memberId, page);

        return ResponseEntity.ok(shareItemDtoList);
    }

    @RequestMapping(
            value = "/api/shareItem/{memberKey}",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<ShareItemDto>> getShareItemByMemberKey(@PathVariable("memberKey") Long memberKey) {
        try {
            List<ShareItemDto> shareItemDtoList = shareItemService.getShareItemByMemberKey(memberKey);

            return ResponseEntity.ok(shareItemDtoList);
        } catch(Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
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



    // PUT
    @RequestMapping(
            value = "/api/shareItem",
            method = RequestMethod.PUT,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> updateShareItem(@RequestParam("file") MultipartFile file, @RequestParam String items) {
        try {
            shareItemService.updateShareItem(file, items);

            return ResponseEntity.ok().build();
        } catch (Exception e ) {
            e.printStackTrace();
            // TODO: 2020/04/15 메시지 처리
            return ResponseEntity.badRequest().build();
        }
    }



    // DELETE
    @RequestMapping(
            value = "/api/shareItem/{shareItemId}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> deleteShareItemById(@PathVariable("shareItemId") Long shareItemId) {
        try {
            shareItemService.deleteShareItemById(shareItemId);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
    }
}
