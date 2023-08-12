package com.example.cookin.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "member")
public class MemberEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    //M : 남자 / F : 여자
    @Column(nullable = false, length = 1)
    private String gender;

    @Embedded
    private bizInfo bizInfo;


}
