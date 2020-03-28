package com.gomawa.gomawa.service;

import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShareItemService {

    @Autowired
    private ShareItemRepository shareItemRepository;

    public ShareItem addShareItem(ShareItem shareItem) {
        System.out.println("shareItem = " + shareItem);
        return shareItemRepository.save(shareItem);
    }
}
