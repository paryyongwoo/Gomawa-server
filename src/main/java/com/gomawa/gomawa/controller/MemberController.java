package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE },
        consumes = { "application/x-www-form-urlencoded" }
        )
public class MemberController {

    @Autowired
    private MemberService memberService;

    // POST
    @RequestMapping(
            value = "/api/member",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    ) // 로그인 후 최초 1회만 실행되는 addMember - NickName 설정 기능
    public ResponseEntity addMemberOnStart(@RequestBody Member memberParam) {
        try{
            if (memberParam == null) throw new Exception("memberParam is null");

            // 매개변수로 받아온 Member의 Key값
            Long key = memberParam.getKey();
            if (key <= 0) throw new Exception("invalid key value");

            Member member = memberService.addMemberOnStart(key, memberParam);

            return ResponseEntity.ok(member);
        } catch (Exception e) {
            e.printStackTrace();

            // TODO: 2020-04-07 응답 에러 메시지를 어떻게 보낼것인지 고민해보기
            Map<String, String> response = new HashMap<>();
            response.put("msg", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }



    // PUT
    @RequestMapping(
            value = "/api/nickName",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT
    ) //
    public ResponseEntity setNickName(@RequestBody Member memberParam) {
        try {
            if (memberParam == null) throw new Exception("memberParam is null");

            // 매개변수로 받아온 Member의 키값
            Long key = memberParam.getKey();
            if (key <= 0) throw new Exception("invalid key value");

            // 매개변수로 받아온 Member의 닉네임값
            String nickName = memberParam.getNickName();

            // 반환될 Member - 닉네임이 수정되어 있는 Member
            Member member = memberService.setNickName(key, nickName);

            return ResponseEntity.ok(member);
        } catch (Exception e) {
            e.printStackTrace();

            // TODO: 2020-04-06 응답 에러 메시지를 어떻게 보낼것인지 고민해보기
            Map<String, String> response = new HashMap<>();
            response.put("msg", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}