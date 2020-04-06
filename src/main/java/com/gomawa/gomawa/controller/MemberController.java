package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        // 반환되는 Member
        Member member = null;

        // 매개변수로 받아온 Member의 Key값
        Long key = memberParam.getKey();

        // 위 Key 값으로 찾은 Member. 없으면 Null 값이 들어가있음
        Member findedMember = memberService.getMemberByKey(key);

        if (findedMember == null) {
            // 위 Key 값으로 가입된 Member 가 없다면 ~ 매개변수로 받아온 Member를 DB에 추가하고, 해당 Member 를 반환
            member = memberService.addMember(memberParam);
        } else {
            // 위 Key 값으로 가입된 Member 가 있다면 ~ 새로 DB에 추가하는 것 없이, DB에 있는 Member를 반환
            member = findedMember;
        }

        return ResponseEntity.ok(member);
    }

    // PUT
    @RequestMapping(
            value = "/api/nickName",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT
    ) //
    public ResponseEntity setNickName(@RequestBody Member memberParam) {
        // 반환되는 Member
        Member member = null;

        // 매개변수로 받아온 Member의 키값으로 DB에 저장된 Member 를 찾음
        member = memberService.getMemberByKey(memberParam.getKey());

        if(member == null) {
            // todo: 매개변수의 키값으로 DB에 저장된 Member를 찾지 못한 경우의 예외 처리
            System.out.println("DB에서 가져온 Member의 값이 Null입니다.");
        } else {
            // 매개변수로 받아온 Member의 닉네임을 DB에서 가져온 Member의 닉네임에 대입
            member.setNickName(memberParam.getNickName());

            // 닉네임 값만 바뀐 Member를 save한다
           memberService.addMember(member);
            System.out.println("@#@#@#@#@#@#@#@#@#@#@#");
        }

        return ResponseEntity.ok(member);
    }
}