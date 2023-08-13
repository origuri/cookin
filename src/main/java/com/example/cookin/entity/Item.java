package com.example.cookin.entity;


import com.example.cookin.entity.enums.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Item extends Base {

    @Id @GeneratedValue
    private Long itemId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String origin; // 원산지

    @Column(nullable = false)
    private String unit; // 단위

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    private String notice; // 특이사항 -> 필수아님

    @Enumerated(EnumType.STRING)
    private Status itemStatus; // 구매가능 여부

    @Column(nullable = false)
    private String category; // 카테고리






}
