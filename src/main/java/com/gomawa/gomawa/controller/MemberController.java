package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.service.MemberService;
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

    // GET
    @RequestMapping(
            value = "/api/member/{key}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public ResponseEntity getMember(@PathVariable Long key) {
        System.out.println("getMember");
        return ResponseEntity.ok(memberService.getMemberByKey(key));
    }

    @RequestMapping(
            value = "/api/member",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public ResponseEntity addMember(@RequestBody Member memberParam) {
        Member member = null;
        try {
            member = memberService.addMember(memberParam);
            System.out.println("member = " + member);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(member);
    }
}
