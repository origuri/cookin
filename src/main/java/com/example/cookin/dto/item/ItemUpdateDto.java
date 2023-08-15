package com.example.cookin.dto.item;

import com.example.cookin.entity.Category;
import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemUpdateDto {
    private String name;

    private String origin; // 원산지

    private String unit; // 단위

    private int price;

    private int quantity;

    private String notice; // 특이사항 -> 필수아님

    private String keep; //보관방법

    private String itemStatus; // 구매가능 여부

    private Long categoryId; // categoryId 필드 추가

    public Category getCategory() {
        return Category.builder().categoryId(categoryId).build();
    }
}
