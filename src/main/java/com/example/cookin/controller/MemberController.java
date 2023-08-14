package com.example.cookin.controller;

import com.example.cookin.auth.PrincipalDetails;
import com.example.cookin.dto.member.MemberInfoDto;
import com.example.cookin.dto.member.MemberJoinDto;
import com.example.cookin.dto.member.MemberModifyDto;
import com.example.cookin.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    /*
    * 회원 가입
    * */
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

    /*
    * 로그인 후 main 페이지로 이동
    * */
    @GetMapping("/cookin")
    public ResponseEntity<?> memberDetailByUsername(@AuthenticationPrincipal PrincipalDetails principalDetails){
        MemberInfoDto memberInfoDto = MemberInfoDto.toMemberInfoDto(principalDetails);
        return new ResponseEntity<>(memberInfoDto, HttpStatus.OK);
    }


    /*
    * 수정 후에 react에서 다시 mypage로 되돌림.
    * */
    @PutMapping("/user/mypage/{memberUid}")
    public ResponseEntity<?> memberModifyByMemberDto(@RequestBody MemberModifyDto memberModifyDto,
                                                     @PathVariable("memberUid") Long memberUid
                                                    // @AuthenticationPrincipal PrincipalDetails principalDetails
                                                     ){
        //memberModifyDto.setMemberUid(principalDetails.getMemberId());
        memberModifyDto.setMemberUid(memberUid);
        int result = memberService.modifyMemberByMemberDto(memberModifyDto);
        if(result == 1) {
            return new ResponseEntity<>("수정성공햇어용", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("비밀번호 확인하세용", HttpStatus.BAD_REQUEST);
        }
    }

}
