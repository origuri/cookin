package com.example.cookin.service;

import com.example.cookin.dto.member.MemberJoinDto;
import com.example.cookin.dto.member.MemberModifyDto;
import com.example.cookin.entity.Member;
import com.example.cookin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    // 회원가입
    public Long join(MemberJoinDto memberJoinDto) {

        String hashPassword = encodePassword(memberJoinDto.getPassword());
        memberJoinDto.setPassword(hashPassword);

        Member member = Member.toJoinMemberEntity(memberJoinDto);
        Long memberId = memberRepository.save(member).getId();
        return memberId;
    }


    public int modifyMemberByMemberDto(MemberModifyDto memberModifyDto) {

        String encodePass = memberRepository.findPasswordById(memberModifyDto.getMemberUid());
        log.info("이거 확인 -> {}",encodePass);
        log.info("이거 확인 -> {}",memberModifyDto);
        String rawPassword = memberModifyDto.getPassword();
        boolean checkedPassword = bCryptPasswordEncoder.matches(rawPassword, encodePass);
        if(checkedPassword){
            Member member = memberRepository.findById(memberModifyDto.getMemberUid()).get();
            member.memberUpdate(memberModifyDto);
            return 1;
        }else{
            return 2;
        }

    }
}
