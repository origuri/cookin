package com.example.cookin.dto.member;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberModifyDto {

    private Long memberUid;
    private String password;
    private String tel;
}
