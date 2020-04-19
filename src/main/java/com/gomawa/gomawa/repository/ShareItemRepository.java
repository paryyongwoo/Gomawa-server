package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShareItemRepository extends JpaRepository<ShareItem, Long> {
    // ID 값으로 게시글 가져오는 메소드
    Optional<ShareItem> findById(Long id);

    // Member Key 값으로 모든 게시글을 가져오는 메소드
    List<ShareItem> findAllByMember(Member member);
}
