package com.example.cookin.entity;

import lombok.Getter;

import javax.persistence.Embeddable;


@Embeddable
@Getter
public class bizInfo {

    private String bizName; // 사업장 이름.
    private String bizId;   // 사업자 등록번호
    private String bizTel;  // 사업장 번호
    private String city;
    private String street;
    private String zipcode;
}
