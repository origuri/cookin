package com.example.cookin.entity;

import com.example.cookin.dto.member.MemberJoinDto;
import com.example.cookin.dto.member.MemberModifyDto;
import lombok.*;

import javax.persistence.*;
@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "member")
public class Member extends Base {

    @Id @GeneratedValue
    @Column(name = "memberuid")
    private Long id;

    @Column(unique = true)
    private String username;// 사번

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    //M : 남자 / F : 여자
    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String memberStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;


    // 회원가입
    public static Member toJoinMemberEntity(MemberJoinDto memberJoinDto){
        return Member.builder()
                .username(memberJoinDto.getUsername())
                .password(memberJoinDto.getPassword())
                .name(memberJoinDto.getName())
                .gender(memberJoinDto.getGender())
                .tel(memberJoinDto.getTel())
                .role(memberJoinDto.getRole())
                .memberStatus(memberJoinDto.getMemberStatus())
                .build();
    }

    // 정보 수정
    public void memberUpdate(MemberModifyDto memberModifyDto){
        this.tel = memberModifyDto.getTel();
    }

}
