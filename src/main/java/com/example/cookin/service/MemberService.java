package com.example.cookin.service;

import com.example.cookin.dto.member.MemberDeleteDto;
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
    private Boolean checkPassword(Long memberUid, String rawPassword){
        String encodePass = memberRepository.findPasswordById(memberUid);
        return bCryptPasswordEncoder.matches(rawPassword, encodePass);
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

        boolean checkedPass = checkPassword(memberModifyDto.getMemberUid(), memberModifyDto.getPassword());
        if(checkedPass){
            Member member = memberRepository.findById(memberModifyDto.getMemberUid()).get();
            member.memberUpdate(memberModifyDto);
            return 1;
        }else{
            return 2;
        }

    }

    /*
     * 회원 탈퇴(퇴사)
     * 파라미터 : memberUid, password
     * */
    public int removeMemberByMemberUid(Long memberUid, MemberDeleteDto memberDeleteDto) {

        boolean checkedPass = checkPassword(memberUid,memberDeleteDto.getPassword());
        Member member = memberRepository.findById(memberUid).get();
        if(checkedPass && member!=null){
            memberRepository.deleteById(memberUid);
            return 1;
        }else if(!checkedPass){
            return 2;
        }else{
            return 3;
        }
    }
}
