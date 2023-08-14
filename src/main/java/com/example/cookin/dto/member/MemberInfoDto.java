package com.example.cookin.dto.member;

import com.example.cookin.auth.PrincipalDetails;
import com.example.cookin.entity.Member;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberInfoDto {

    private Long memberUid;
    private String username;
    private String name;
    private String bizName;

    public static MemberInfoDto toMemberInfoDto(PrincipalDetails principalDetails){
        return MemberInfoDto.builder()
                .memberUid(principalDetails.getMemberId())
                .username(principalDetails.getUsername())
                .name(principalDetails.getMemberName())
                .build();
    }

}
