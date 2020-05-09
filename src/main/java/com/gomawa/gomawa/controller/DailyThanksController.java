package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.dto.DailyThanksDto;
import com.gomawa.gomawa.service.MyThanksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            DailyThanksDto dailyThanks = myThanksService.getDailyThanks(memberId);

            /**
             * 오늘 작성한 DailyThanks가 없는 경우 null 반환
             *  => noContent(204 code)로 응답
             */
            if (dailyThanks == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(dailyThanks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
