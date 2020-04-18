package com.gomawa.gomawa.repository;

import com.gomawa.gomawa.entity.Comment;
import com.gomawa.gomawa.entity.ShareItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByShareItem(ShareItem shareItem);
}
