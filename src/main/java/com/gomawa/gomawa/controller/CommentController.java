package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.CommentDto;
import com.gomawa.gomawa.dto.ShareItemDto;
import com.gomawa.gomawa.entity.Comment;
import com.gomawa.gomawa.entity.ShareItem;
import com.gomawa.gomawa.service.CommentService;
import com.gomawa.gomawa.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE }
        //,consumes = { "application/x-www-form-urlencoded" }
        )
public class CommentController {

    @Autowired
    private CommentService commentService;

    // GET
    @RequestMapping(
            value = "/api/comment/{shareItemId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<CommentDto>> getCommentByShareItemId(@PathVariable("shareItemId") Long shareItemId) {
        try{
            List<CommentDto> commentDtoList = commentService.getCommentByShareItemId(shareItemId);

            return ResponseEntity.ok(commentDtoList);
        } catch(Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
    }

    // POST
    @RequestMapping(
            value = "/api/comment",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity<CommentDto> addComment(@RequestBody Comment comment) {
        try {
            CommentDto commentDto = commentService.addComment(comment);

            return ResponseEntity.ok(commentDto);
        } catch(Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }

    }

    // DELETE
    @RequestMapping(
            value = "/api/comment/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> deleteCommentById(@PathVariable("id") Long id) {
        try {
            commentService.deleteCommentById(id);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
    }
}