package com.example.cookin.controller;

import com.example.cookin.dto.member.MemberJoinDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails){

        if(userDetails == null){
            System.out.println("우선 그냥 홈페이지");
        }else{
            System.out.println("로그인함. -> "+userDetails.getUsername());
        }
        return "home";
    }
    @GetMapping("/aaa")
    public String fail(){
        return "loginForm";
    }
    @GetMapping("/bbb")
    public String sss(){
        return "bbb";
    }

    @GetMapping("/cookina")
    public String ddd(@AuthenticationPrincipal UserDetails userDetails){

        return "bbb";
    }
}
