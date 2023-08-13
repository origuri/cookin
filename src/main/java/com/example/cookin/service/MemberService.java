package com.example.cookin.service;

import com.example.cookin.dto.member.MemberJoinDto;
import com.example.cookin.entity.Member;
import com.example.cookin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private String encodePassword(String password){
        String rawPassword = password;
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public Long join(MemberJoinDto memberJoinDto) {

        String hashPassword = encodePassword(memberJoinDto.getPassword());
        memberJoinDto.setPassword(hashPassword);

        Member member = Member.toJoinMemberEntity(memberJoinDto);
        Long memberId = memberRepository.save(member).getId();
        return memberId;
    }
}
