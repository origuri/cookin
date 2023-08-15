package com.example.cookin.dto.item;

import lombok.Data;

@Data
public class ItemSearchDto {
    private String name;
    private String itemStatus;
    private String large; // 대분류
    private String mid; // 중분류
}
