package com.gomawa.gomawa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.model.ShareItemModel;
import com.gomawa.gomawa.repository.ShareItemRepository;
import com.gomawa.gomawa.service.ShareItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(
        produces = MediaTypes.HAL_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ShareItemController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShareItemService shareItemService;

    @RequestMapping(
            value = "/api/shareItem",
            method = RequestMethod.POST
    )
    public ResponseEntity<ShareItemModel> addShareItem(@RequestBody ShareItem shareItemParam, @RequestParam("file") MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        System.out.println("shareItemParam = " + shareItemParam + " fileName: " + fileName);

        /**
         * ShareItem 저장
         */
        ShareItem createdShareItem = shareItemService.addShareItem(shareItemParam);

        /**
         * response header의 location에 들어갈 uri로
         * location 속성에 shareItem을 추가하는 링크 주소가 들어간다
         */
        URI createdUri = linkTo(ShareItemController.class).slash("{id}").toUri();

        ShareItemModel shareItemModel = modelMapper.map(createdShareItem, ShareItemModel.class);
        shareItemModel.add(linkTo(ShareItemController.class).slash(shareItemModel.getId()).withSelfRel());

        return ResponseEntity.created(createdUri).body(shareItemModel);

    }
}
