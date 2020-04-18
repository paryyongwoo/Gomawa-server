package com.gomawa.gomawa.service;

import com.gomawa.gomawa.dto.CommentDto;
import com.gomawa.gomawa.entity.Comment;
import com.gomawa.gomawa.entity.Likes;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.repository.CommentRepository;
import com.gomawa.gomawa.repository.LikeRepository;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.repository.ShareItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ShareItemRepository shareItemRepository;

    public List<CommentDto> getCommentByShareItemId(Long shareItemId) throws Exception {
        // ShareItemId 로 ShareItem 가져옴
        ShareItem shareItem = shareItemRepository.findById(shareItemId).orElse(null);
        if(shareItem == null) { throw new Exception("shareItem is null"); }

        // ShareItem 으로 Comment 전부 가져옴
        List<Comment> commentList = commentRepository.findAllByShareItem(shareItem);

        // DTO List 로 변환
        int size = commentList.size();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(int i=0; i<size; i++) {
            commentDtoList.add(commentList.get(i).entityToDto());
        }

        return commentDtoList;
    }

    public CommentDto addComment(Comment comment) throws Exception {
        if(comment == null) { throw new Exception("comment is null"); }

        // 클라이언트에서 받아온 Comment 의 Content 와 regDate, 그리고 ShareItem's ID , Member's Key 를 이용해 얻은 Entitiy 들로 새 Comment 를 만듦
        // 영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!영속성!
        // 가져온 Comment 의 member 와 shareItem 은 영속성이 부여되지 않은, DB의 입장에선 완전히 새로운 데이터! 그렇기 때문에 영속성 관련 에러가 짜증나게 많이 나옴!
        // 근데 가져온 Comment 그대로 썼을 때, Comment Entitiy 의 member 에 cascade 속성을 부여하니까 이해할 수 없는 결과가 발생
        // 왜 shareItem 에 부여하면 안되고, member에 부여하면 되었나? 짜증난다 진짜 짜증난다 진짜 짜증난다 진짜
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setRegDate(comment.getRegDate());
        newComment.setShareItem(shareItemRepository.findById(comment.getShareItem().getId()).orElse(null));
        newComment.setMember(memberRepository.findByKey(comment.getMember().getKey()).orElse(null));

        Comment commentFromDb = commentRepository.save(newComment);

        System.out.println(commentFromDb.getId());

        // DTO 로 변환
        CommentDto commentDto = commentFromDb.entityToDto();

        return commentDto;
    }
}
