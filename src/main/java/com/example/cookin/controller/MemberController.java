package com.example.cookin.controller;

import com.example.cookin.dto.member.MemberJoinDto;
import com.example.cookin.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberJoinDto memberJoinDto){
        log.info("잘 넘어왔는지 확인 -> {}",memberJoinDto);
        Long memberId = memberService.join(memberJoinDto);
        if(memberId > 0){
            return new ResponseEntity<>("회원가입 성공 201", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("회원가입 실패 400", HttpStatus.BAD_REQUEST);
        }
    }

}
