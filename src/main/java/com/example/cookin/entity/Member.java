package com.example.cookin.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "member")
public class Member {

    @Id @GeneratedValue
    @Column(name = "memberuid")
    private Long id;

    @Column(unique = true)
    private String username;

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

    @Embedded
    private bizInfo bizInfo;


}
