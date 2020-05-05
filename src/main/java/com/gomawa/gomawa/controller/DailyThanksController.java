package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.DailyThanksDto;
import com.gomawa.gomawa.entity.DailyThanks;
import com.gomawa.gomawa.service.MyThanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class DailyThanksController {

    @Autowired
    private MyThanksService myThanksService;

    @RequestMapping(
            value = "/api/dailyThanks/{memberId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<DailyThanksDto> getDailyThanks(@PathVariable("memberId") Long memberId) {
        DailyThanksDto dailyThanks = myThanksService.getDailyThanks(memberId);
        System.out.println("dailyThanks = " + dailyThanks);
        return ResponseEntity.ok(dailyThanks);
    }


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
