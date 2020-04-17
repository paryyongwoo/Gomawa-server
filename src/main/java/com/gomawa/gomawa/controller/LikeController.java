package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.dto.ShareItemDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.service.LikeService;
import com.gomawa.gomawa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE }
        //,consumes = { "application/x-www-form-urlencoded" }
        )
public class LikeController {

    @Autowired
    private LikeService likeService;

    // PUT
    @RequestMapping(
            value = "/api/like/{memberKey}/{shareItemId}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<ShareItemDto> updateLike(@PathVariable("memberKey") Long memberKey, @PathVariable("shareItemId") Long shareItemId) {
        try {
            ShareItem shareItem = likeService.updateLike(memberKey, shareItemId);

            ShareItemDto shareItemDto = shareItem.entityToDto();

            return ResponseEntity.ok(shareItemDto);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}