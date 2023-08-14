package com.example.cookin.dto.member;

import com.example.cookin.entity.Store;
import lombok.*;



@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberJoinDto {

    private Long memberUid;
    private String username;
    private String password;
    private String name;
    //M : 남자 / F : 여자
    private String gender;
    private String tel;
    private String role;
    private String memberStatus;
    private Store bizInfo;
}
