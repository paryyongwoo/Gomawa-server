package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(
        produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE }
        //,consumes = { "application/x-www-form-urlencoded" }
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

            MemberDto memberDto = member.entityToDto();

            return ResponseEntity.ok(memberDto);
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

            /**
             * Member -> MemberDto로 변환
             */
            // TODO: 2020/04/15 converter를 통해 MemberDto로 전환하게 로직 수정 필요
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail(member.getEmail());
            memberDto.setNickName(member.getNickName());
            memberDto.setGender(member.getGender());
            memberDto.setKey(member.getKey());
            memberDto.setRegDate(member.getRegDate());

            return ResponseEntity.ok(memberDto);
        } catch (Exception e) {
            e.printStackTrace();

            // TODO: 2020-04-06 응답 에러 메시지를 어떻게 보낼것인지 고민해보기
            Map<String, String> response = new HashMap<>();
            response.put("msg", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }



    // DELETE
    @RequestMapping(
            value = "api/member/{key}",
            method = RequestMethod.DELETE
    ) // Key 값으로 Member 를 찾아 DELETE 하는 메소드
    public void deleteMemberByKey(@PathVariable("key") Long key) {
        try {
            System.out.println(key);

            memberService.deleteMemberByKey(key);
        } catch(Exception e) {
            e.printStackTrace();

            // TODO: 2020-04-11 데이터 삭제 시 에러나면 어떻게 에러 메세지를 보내야 하는가
        }

        // TODO: 2020-04-12 HATEOAS 적용
    }
}