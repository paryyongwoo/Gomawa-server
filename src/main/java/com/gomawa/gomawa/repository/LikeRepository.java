package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.Like;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndShareItem(Member member, ShareItem shareItem);
}
