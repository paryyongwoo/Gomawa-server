package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.Likes;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByMemberAndShareItem(Member member, ShareItem shareItem);
}
