package com.gomawa.gomawa.assembler;

import com.gomawa.gomawa.controller.ShareItemController;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.model.ShareItemModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ShareItemAssembler extends RepresentationModelAssemblerSupport<ShareItem, ShareItemModel> {

    public ShareItemAssembler() {
        super(ShareItemController.class, ShareItemModel.class);
    }

    @Override
    public ShareItemModel toModel(ShareItem entity) {
        ShareItemModel shareItemModel = instantiateModel(entity);

        // 조회 용도로 사용?
//        shareItemModel.add(linkTo(methodOn(ShareItemController.class).addShareItem(entity)).withSelfRel());

        shareItemModel.setId(entity.getId());
        shareItemModel.setContent(entity.getContent());
        shareItemModel.setMember(entity.getMember());
        //shareItemModel.setRegDate(entity.getRegDate());
        shareItemModel.setBackgroundUrl(entity.getBackgroundUrl());
        shareItemModel.setLikeNum(entity.getLikeNum());

        return shareItemModel;
    }

    @Override
    public CollectionModel<ShareItemModel> toCollectionModel(Iterable<? extends ShareItem> entities) {
        return super.toCollectionModel(entities);
    }
}
