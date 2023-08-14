package com.example.cookin.auth;

import com.example.cookin.dto.member.MemberJoinDto;
import com.example.cookin.entity.Member;
import com.example.cookin.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MemberRepository memberRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // userDetails 객체에 값을 다 넣어서
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // member의 정보를 가져옴.
        Member member = memberRepository.findByUsername(userDetails.getUsername());
        // 여기서 member로 userDetails타입이 아닌 Principal 타입으로 가져와서 내가 만들 거임.

        response.sendRedirect("/");
    }
}
