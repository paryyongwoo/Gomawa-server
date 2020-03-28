package com.gomawa.gomawa.controller;

import com.gomawa.gomawa.common.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    AppProperties appProperties;

    /**
     * 로그인 페이지 요청 url을 얻기 위한 핸들러
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.GET)
    public ResponseEntity getLoginPageUrl() throws UnsupportedEncodingException {
        /**
         *  로그인 페이지 url을 얻기 위해 필요한 항목
         *  1. client_id
         *  2. redirect_url(callback url)
         *  3. state(상태 토큰)
         */

        // client_id 준비
        String clientId = this.appProperties.getLoginClientIdNaver();

        // redirect_url 준비
        String redirectUrl = URLEncoder.encode(this.appProperties.getLoginRedirectUrlNaver(), "UTF-8");

        // state 준비
        String stateToken = generateState();

        // 리턴할 로그인 페이지 요청 url 만들기
        String url = "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&response_type=code&redirect_uri=" + redirectUrl + "&state=" + stateToken;

        /**
         * to json
         * 다른방법으로 json형태의 응답을 보내는 방법?
         */
        Map<String, String> result = new HashMap<>();
        result.put("url", url);

        ResponseEntity<Object> responseEntity = new ResponseEntity(result, HttpStatus.OK);

        return responseEntity;
    }

    /**
     * 네이버 로그인 요청을 할때 함께 보낼 상태 토큰(state)을 생성해주는 메소드
     */
    private String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}
