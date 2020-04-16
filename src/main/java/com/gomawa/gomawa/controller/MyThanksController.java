package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.DailyThanksDto;
import com.gomawa.gomawa.dto.MemberDto;
import com.gomawa.gomawa.entity.DailyThanks;
import com.gomawa.gomawa.entity.Member;
import com.gomawa.gomawa.repository.MemberRepository;
import com.gomawa.gomawa.service.MyThanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
)
public class MyThanksController {

    @Autowired
    private MyThanksService myThanksService;

    @Autowired
    private MemberRepository memberRepository;


    @RequestMapping(
            value= "/api/dailyThanks",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addDailyThanks(@RequestBody DailyThanksDto dailyThanksDto) {
        DailyThanksDto savedDailyThanksDto = myThanksService.addDailyThanks(dailyThanksDto);
        return ResponseEntity.ok(savedDailyThanksDto);
    }

}
